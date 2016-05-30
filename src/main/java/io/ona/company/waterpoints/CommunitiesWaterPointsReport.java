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

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + brokenWaterPoints;
        result = prime * result
                + ((communityNameToCommunityWaterPointsMap == null) ? 0
                        : communityNameToCommunityWaterPointsMap.hashCode());
        result = prime * result + workingWaterPoints;
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommunitiesWaterPointsReport other = (CommunitiesWaterPointsReport) obj;
        if (brokenWaterPoints != other.brokenWaterPoints)
            return false;
        if (communityNameToCommunityWaterPointsMap == null) {
            if (other.communityNameToCommunityWaterPointsMap != null)
                return false;
        } else if (!communityNameToCommunityWaterPointsMap
                .equals(other.communityNameToCommunityWaterPointsMap))
            return false;
        if (workingWaterPoints != other.workingWaterPoints)
            return false;
        return true;
    }

    @Override
    public String toString() {

        return "CommunitiesWaterPointsReport [communityNameToCommunityWaterPointsMap="
                + communityNameToCommunityWaterPointsMap
                + ", workingWaterPoints=" + workingWaterPoints
                + ", brokenWaterPoints=" + brokenWaterPoints + "]";
    }
}
