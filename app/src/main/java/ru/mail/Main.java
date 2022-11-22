package ru.mail;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import ru.mail.common.FlywayInitializer;
import ru.mail.server_core.ApplicationServletModule;
import ru.mail.server_core.DefaultServer;
import ru.mail.server_core.login.SecurityHandlerBuilder;

import javax.servlet.DispatcherType;
import java.net.URL;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        FlywayInitializer.initDb();
        final var injector = Guice.createInjector(new ApplicationModule(),
                new ApplicationServletModule());
        final var server = injector.getInstance(DefaultServer.class).build();

        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
        context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        context.setContextPath("/");
        final URL resource = LoginService.class.getResource("/static");
        context.setBaseResource(Resource.newResource(resource.toExternalForm()));
        context.addServlet(DefaultServlet.class, "/");

        final String jdbcConfig = Main.class.getResource("/db/jdbc_config").toExternalForm();
        final JDBCLoginService jdbcLoginService = new JDBCLoginService("login", jdbcConfig);
        final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(jdbcLoginService);
        server.addBean(jdbcLoginService);
        securityHandler.setHandler(context);
        server.setHandler(securityHandler);


        server.start();
    }
}