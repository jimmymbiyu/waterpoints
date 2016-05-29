package io.ona.company.waterpoints;

/**
 * Details the status of the water points for one community.
 * 
 * @author Ndung'u Mbiyu
 *
 */
public class CommunityWaterPoints {

    private int functionalWaterPoints = 0;
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
    public String toString() {

        return "WaterPoint [functionalWaterPoints=" + functionalWaterPoints
                + ", brokenWaterPoints=" + brokenWaterPoints + "]";
    }
}
