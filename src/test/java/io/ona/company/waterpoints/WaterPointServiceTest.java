package io.ona.company.waterpoints;

import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.ona.company.waterpoints.config.AppConfig;
import io.ona.company.waterpoints.config.StartServer;

@ContextConfiguration(classes = AppConfig.class)
public class WaterPointServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WaterPointService waterPointService;
    StartServer server;

    @BeforeClass
    public void startServer() throws Exception {

        server = new StartServer();
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
    }
}