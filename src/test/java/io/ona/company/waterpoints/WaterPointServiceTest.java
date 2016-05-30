package io.ona.company.waterpoints;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.ona.company.waterpoints.config.AppConfig;
import io.ona.company.waterpoints.config.WaterPointsServer;

@ContextConfiguration(classes = AppConfig.class)
public class WaterPointServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WaterPointService waterPointService;
    WaterPointsServer server;

    @BeforeClass
    public void startServer() throws Exception {

        server = new WaterPointsServer();
        server.start(8091);
    }

    @AfterTest
    public void stopServer() throws Exception {

        server.stop();
    }

    @Test
    public void testGetWaterPointSummaryReport() throws Exception {

        CommunitiesWaterPointsReport report =
                waterPointService.getWaterPointSummaryReport();

        assertNotNull(report);
        assertTrue(report.getCommunities().size() > 0);
    }

    @Test
    public void testGetWaterPointSummaryReport_IsConsistent() throws Exception {

        CommunitiesWaterPointsReport report =
                waterPointService.getWaterPointSummaryReport();

        CommunitiesWaterPointsReport report2 =
                waterPointService.getWaterPointSummaryReport();

        assertTrue(report.equals(report2));
    }
}
