package ru.mail.server_core;

import com.google.inject.servlet.ServletModule;

public class ApplicationServletModule extends ServletModule {
    protected void configureServlets() {
        bind(DefaultServer.class);
        serve("/products").with(DataServlet.class);
    }
}
