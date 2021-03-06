package io.ona.company.waterpoints;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.ona.company.waterpoints.json.WaterPointJsonHelper;

/**
 * Queries the remote service
 * https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.
 * json, and forwards the result to {@link WaterPointJsonHelper} for analysis.
 * 
 * @author Ndung'u Mbiyu
 *
 */
@Controller
public class WaterPointService {

    private Logger logger = Logger.getLogger(WaterPointService.class);
    public static String WATER_POINT_SERVICE_ENDPOINT =
            "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";

    @Autowired
    private WaterPointJsonHelper waterPointJsonHelper;

    private HttpClient httpClient;

    public WaterPointService() throws Exception {

        startHttpClient();
    }

    private void startHttpClient() throws Exception {

        SslContextFactory sslContextFactory = new SslContextFactory();
        httpClient = new HttpClient(sslContextFactory);
        httpClient.start();
    }

    @RequestMapping(value = "/summary.do", produces = "application/json")
    @ResponseBody
    public CommunitiesWaterPointsReport getWaterPointSummaryReport()
            throws Exception {

        logger.info("Serving request /summary.do");
        String json = sendGET();
        CommunitiesWaterPointsReport waterPointSummaryReport =
                waterPointJsonHelper.processJson(json);

        return waterPointSummaryReport;
    }

    private String sendGET()
            throws TimeoutException, ExecutionException, InterruptedException {

        logger.info("Querying remote service: " + WATER_POINT_SERVICE_ENDPOINT);
        ContentResponse contentResponse =
                httpClient.GET(WATER_POINT_SERVICE_ENDPOINT);

        String content = contentResponse.getContentAsString();
        return content;
    }
}
