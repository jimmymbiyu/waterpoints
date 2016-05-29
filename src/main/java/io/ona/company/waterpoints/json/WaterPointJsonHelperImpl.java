package io.ona.company.waterpoints.json;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import io.ona.company.waterpoints.CommunitiesWaterPointsReport;

@Component
public class WaterPointJsonHelperImpl implements WaterPointJsonHelper {

    private static String COMMUNITIES_VILLAGES_JSON_KEY =
            "communities_villages";
    private static String WATER_FUNCTIONING_JSON_KEY = "water_functioning";

    public WaterPointJsonHelperImpl() {
    }

    @Override
    public CommunitiesWaterPointsReport processJson(String json)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readValue(json, JsonNode.class);
        Iterator<JsonNode> records = node.elements();

        CommunitiesWaterPointsReport waterPointSummaryReport =
                new CommunitiesWaterPointsReport();
        while (records.hasNext()) {
            JsonNode record = records.next();

            String village =
                    record.get(COMMUNITIES_VILLAGES_JSON_KEY).asText();

            String rawStatus =
                    record.get(WATER_FUNCTIONING_JSON_KEY).asText();

            WaterPointStatus waterPointStatus;
            try {
                waterPointStatus = WaterPointStatus.fromString(rawStatus);
            } catch (UnknownWaterPointStatusTextException e) {

                StringBuilder errrorSb = new StringBuilder();
                errrorSb.append("Community ").append(village)
                        .append(" has an invalid value, ")
                        .append(rawStatus)
                        .append(", for the key ")
                        .append(WATER_FUNCTIONING_JSON_KEY);
                System.err.println(errrorSb.toString());
                continue;
            }

            waterPointSummaryReport.updateCommunityWaterPoints(village,
                    waterPointStatus);

        }

        return waterPointSummaryReport;
    }

}
