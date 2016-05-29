package io.ona.company.waterpoints;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.ona.company.waterpoints.json.WaterPointStatus;

@Component
public class CommunitiesWaterPointsReport {

    private Map<String, CommunityWaterPoints> communityNameToCommunityWaterPointsMap;
    private int workingWaterPoints;
    private int brokenWaterPoints;

    public CommunitiesWaterPointsReport() {
        communityNameToCommunityWaterPointsMap = new HashMap<>();
    }

    public synchronized void updateCommunityWaterPoints(String communityName,
            WaterPointStatus waterPointStatus) {

        CommunityWaterPoints communityWaterPoints =
                communityNameToCommunityWaterPointsMap.get(communityName);
        if (communityWaterPoints == null) {
            communityWaterPoints = new CommunityWaterPoints();
        }

        if (waterPointStatus.equals(WaterPointStatus.WORKING)) {
            communityWaterPoints.incrementFunctionalWaterPoints();
            workingWaterPoints++;
        } else if (waterPointStatus.equals(WaterPointStatus.NOT_WORKING)) {
            communityWaterPoints.incrementBrokenWaterPoints();
            brokenWaterPoints++;
        }

        communityNameToCommunityWaterPointsMap.put(communityName,
                communityWaterPoints);
    }

    public Map<String, CommunityWaterPoints> getWaterPoints() {

        return communityNameToCommunityWaterPointsMap;
    }

    public int getWorkingWaterPoints() {

        return workingWaterPoints;
    }

    public int getBrokenWaterPoints() {

        return brokenWaterPoints;
    }

    public int getTotalWaterPoints() {

        return workingWaterPoints + brokenWaterPoints;
    }
}
