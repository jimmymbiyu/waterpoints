package io.ona.company.waterpoints.json;

import java.io.IOException;

import io.ona.company.waterpoints.CommunitiesWaterPointsReport;

public interface WaterPointJsonHelper {

    CommunitiesWaterPointsReport processJson(String json) throws IOException;
}
