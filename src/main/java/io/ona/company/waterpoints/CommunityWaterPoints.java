package io.ona.company.waterpoints;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Details the status of the water points for one community.
 * 
 * @author Ndung'u Mbiyu
 *
 */
public class CommunityWaterPoints {

    @JsonIgnore
    private int functionalWaterPoints = 0;
    @JsonIgnore
    private int brokenWaterPoints = 0;

    protected CommunityWaterPoints() {
    }

    protected void incrementFunctionalWaterPoints() {

        functionalWaterPoints++;
    }

    protected void incrementBrokenWaterPoints() {

        brokenWaterPoints++;
    }

    public int getTotalWaterPoints() {

        return functionalWaterPoints + brokenWaterPoints;
    }

    public double getRank() {

        double fraction =
                (double) brokenWaterPoints / (double) getTotalWaterPoints();
        return fraction * 100;
    }

    public int getBrokenWaterPoints() {

        return brokenWaterPoints;
    }

    public int getFunctionalWaterPoints() {

        return functionalWaterPoints;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + brokenWaterPoints;
        result = prime * result + functionalWaterPoints;
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
        CommunityWaterPoints other = (CommunityWaterPoints) obj;
        if (brokenWaterPoints != other.brokenWaterPoints)
            return false;
        if (functionalWaterPoints != other.functionalWaterPoints)
            return false;
        return true;
    }

    @Override
    public String toString() {

        return "WaterPoint [functionalWaterPoints=" + functionalWaterPoints
                + ", brokenWaterPoints=" + brokenWaterPoints + "]";
    }
}
