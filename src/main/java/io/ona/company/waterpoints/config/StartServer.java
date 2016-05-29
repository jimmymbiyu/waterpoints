package io.ona.company.waterpoints.config;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This class is the starting class for the service and has a main method in it.
 * <p>
 * If running in an IDE please add all the jars under the lib directory to the
 * classpath.
 * <p>
 * To access the service type in a browser the following uri:
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp; http://localhost:8090/waterpoints/summary.do
 * <p>
 * You will see json rendered in the browser showing the Water Point Summary
 * Report.
 * <p>
 * Please read the README.md for further details of running the service and its
 * unit and integration tests.
 *
 * @author Ndung'u Mbiyu
 */
public class StartServer {

    private Logger logger = Logger.getLogger(StartServer.class);
    private Server server;

    public StartServer() {
    }

    private void configureWebContext(int port) {

        server = new Server(port);

        ServletContextHandler servletContextHolder =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        servletContextHolder.setContextPath("/waterpoints");
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();

        // Setup Spring context
        ContextLoaderListener contextLoaderListener =
                new ContextLoaderListener(context);

        servletContextHolder.addEventListener(contextLoaderListener);
        server.setHandler(servletContextHolder);
        context.register(AppConfig.class);
        context.refresh();

        // Add servlets
        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(context);

        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        servletContextHolder.addServlet(servletHolder, "*.do");
    }

    public void start(int port) throws Exception {

        logger.info("Starting up Server");
        configureWebContext(port);
        server.start();
        logger.info("Successfully started Server. "
                + "Visit http://localhost:8090/waterpoints/summary.do for the "
                + "water point summary.");
    }

    public void stop() throws Exception {

        if (server != null) {
            server.stop();
        }
    }

    public static void main(String[] args) throws Exception {

        StartServer server = new StartServer();
        server.start(8090);
    }

}
