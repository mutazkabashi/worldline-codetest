package com.worldline.codetest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import com.worldline.codetest.repository.DocumentRepositoryMapImp;
import com.worldline.codetest.repository.ProfileRepositoryMapImp;
import com.worldline.codetest.service.DocumentService;
import com.worldline.codetest.service.ProfileService;

/**
 * Main Class which Startup The application and start/create core
 * Classes/Services
 * 
 * @author Mutaz Abdelgadir
 *
 */
public class MainApp {

	static final String APPLICATION_PATH = "/api";
	static final String CONTEXT_ROOT = "/";
	// profileService, and documentService are the main classes which is used to
	// interact with the DB/Map, Making those 2 services Static are antipattern
	// but Since every call to the rest-api-end points create new instance of the
	// api-end point(e.g profileResource)[Note @singelton Dosent work here i don't
	// know why]
	// we need to use static services for all rest-end-point and use the same Map
	// for all end-points to track/make different transactions(create/update/get)
	public static ProfileService profileService;
	public static DocumentService documentService;

	static {
		profileService = new ProfileService(new ProfileRepositoryMapImp());
		documentService = new DocumentService(new DocumentRepositoryMapImp(), profileService);
	}

	public MainApp() {
	}

	public static void main(String[] args) throws Exception {
		try {
			Log.setLog(new Slf4jLog());

			new MainApp().run();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void run() throws Exception {

		final int port = 8080;
		final Server server = new Server(port);

		// Setup the basic Application "context" at "/".
		// This is also known as the handler tree (in Jetty speak).
		final ServletContextHandler context = new ServletContextHandler(server, CONTEXT_ROOT);

		// Setup RESTEasy's HttpServletDispatcher at "/api/*".
		final ServletHolder restEasyServlet = new ServletHolder(new HttpServletDispatcher());
		restEasyServlet.setInitParameter("resteasy.servlet.mapping.prefix", APPLICATION_PATH);
		restEasyServlet.setInitParameter("javax.ws.rs.Application", "com.worldline.codetest.FatJarApplication");
		context.addServlet(restEasyServlet, APPLICATION_PATH + "/*");

		// Setup the DefaultServlet at "/".
		final ServletHolder defaultServlet = new ServletHolder(new DefaultServlet());
		context.addServlet(defaultServlet, CONTEXT_ROOT);

		// Set the path to our static (Swagger UI) resources
		String resourceBasePath = MainApp.class.getResource("/swagger-ui").toExternalForm();
		context.setResourceBase(resourceBasePath);
		context.setWelcomeFiles(new String[] { "index.html" });

		server.start();
		server.join();
	}

}