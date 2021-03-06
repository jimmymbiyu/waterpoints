package io.ona.company.waterpoints.json;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ona.company.waterpoints.CommunitiesWaterPointsReport;

@Component
public class WaterPointJsonHelperImpl implements WaterPointJsonHelper {

    private Logger logger = Logger.getLogger(WaterPointJsonHelperImpl.class);
    private static String COMMUNITIES_VILLAGES_JSON_KEY =
            "communities_villages";
    private static String WATER_FUNCTIONING_JSON_KEY = "water_functioning";

    public WaterPointJsonHelperImpl() {
    }

    @Override
    public CommunitiesWaterPointsReport processJson(String json)
            throws JsonParsingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node;
        try {
            node = objectMapper.readValue(json, JsonNode.class);
        } catch (IOException e1) {
            throw new JsonParsingException(e1);
        }
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
                logger.warn(errrorSb.toString());
                continue;
            }

            waterPointSummaryReport.updateCommunityWaterPoints(village,
                    waterPointStatus);

        }

        return waterPointSummaryReport;
    }

}
