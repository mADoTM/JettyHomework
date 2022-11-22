package ru.mail.server_core;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import ru.mail.dao.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class DataServlet extends HttpServlet {
    protected final @NotNull DataService service;

    protected final @NotNull Gson gson;

    @Inject
    public DataServlet(@NotNull DataService service,
                       @NotNull Gson gson) {

        this.service = service;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mapInJson = gson.toJson(service.getAllCompaniesWithProducts());
        System.out.println("json get");
        resp.setContentType("text/plain");
        resp.getWriter().println(mapInJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String productName = req.getParameter("product_name");
        String companyName = req.getParameter("company_name");
        int count = Integer.parseInt(req.getParameter("count"));

        service.add(productName, companyName, count);

        resp.setStatus(200);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
