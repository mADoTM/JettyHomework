package ru.mail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import org.eclipse.jetty.server.Server;
import ru.mail.dao.CompanyDAO;
import ru.mail.dao.DataService;
import ru.mail.dao.ProductDAO;
import ru.mail.server_core.DefaultServer;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductDAO.class).toInstance(new ProductDAO());
        bind(CompanyDAO.class).toInstance(new CompanyDAO());
        bind(DataService.class);
        bind(Gson.class).toInstance(new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .enableComplexMapKeySerialization()
                .create());
    }


}
