package io.ona.company.waterpoints;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.ona.company.waterpoints.json.WaterPointStatus;

/**
 * Aggregates the information of all the water points for all communities.
 * 
 * @author Ndung'u Mbiyu
 *
 */
@Component
public class CommunitiesWaterPointsReport {

    private Map<String, CommunityWaterPoints> communityNameToCommunityWaterPointsMap;
    private int workingWaterPoints;
    @JsonIgnore
    private int brokenWaterPoints;

    public CommunitiesWaterPointsReport() {
        communityNameToCommunityWaterPointsMap = new TreeMap<>();
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

    public Map<String, CommunityWaterPoints> getCommunities() {

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
