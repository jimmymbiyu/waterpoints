package io.ona.company.waterpoints.json;

import io.ona.company.waterpoints.CommunitiesWaterPointsReport;

public interface WaterPointJsonHelper {

    /**
     * Parses a json String and generates a report of the water points detailed
     * in the input json.
     *
     * @param json
     *            the input json
     * @return a summary of the community and their water points.
     * @throws JsonParsingException if the json cannot be successfully parsed.
     */
    CommunitiesWaterPointsReport processJson(String json)
            throws JsonParsingException;
}
