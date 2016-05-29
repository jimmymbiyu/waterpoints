package io.ona.company.waterpoints.json;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.ona.company.waterpoints.CommunitiesWaterPointsReport;
import io.ona.company.waterpoints.CommunityWaterPoints;

public class WaterPointJsonHelperTest {

    private WaterPointJsonHelper waterPointJsonHelper =
            new WaterPointJsonHelperImpl();

    private static final String JSON_FILE_PATH =
            "io/ona/company/waterpoints/json/water_points.json";

    private String json;

    @BeforeClass
    public void beforeClassSetup() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(JSON_FILE_PATH);
        File jsonFile = new File(resource.getFile());

        StringBuilder jsonStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(jsonFile))) {

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(currentLine);
            }
        }
        json = jsonStringBuilder.toString();
        assertNotNull(json);
    }

    @Test
    public void testProcessJson_OnlyValidWaterPointsAreRecorded()
            throws IOException {

        CommunitiesWaterPointsReport communitiesWaterPointsReport =
                waterPointJsonHelper.processJson(json);

        assertEquals(communitiesWaterPointsReport.getTotalWaterPoints(), 2);
    }

    @Test
    public void testProcessJson_WaterPointCount() throws IOException {

        CommunitiesWaterPointsReport communitiesWaterPointsReport =
                waterPointJsonHelper.processJson(json);

        CommunityWaterPoints communityWaterPoints = communitiesWaterPointsReport
                .getWaterPoints().get("Gumaryili");

        assertEquals(communityWaterPoints.getTotalWaterPoints(), 1);
        assertEquals(communityWaterPoints.getBrokenWaterPoints(), 0);
        assertEquals(communityWaterPoints.getFunctionalWaterPoints(), 1);
        assertEquals(communityWaterPoints.getRank(), 0.0);
    }
}
