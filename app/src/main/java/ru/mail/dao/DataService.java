package ru.mail.dao;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.mail.Company;
import ru.mail.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataService {
    private final CompanyDAO companyDAO;

    private final ProductDAO productDAO;

    @Inject
    public DataService(CompanyDAO companyDAO, ProductDAO productDAO) {
        this.companyDAO = companyDAO;
        this.productDAO = productDAO;
    }

    public void add(@NotNull String productName,
                    @NotNull String companyName,
                    int count) {
        Company company;

        try {
            company = companyDAO.getByName(companyName);
        } catch (IllegalStateException e) {
            companyDAO.save(companyName);
            company = companyDAO.getByName(companyName);
        }

        productDAO.save(productName, company.id, count);
    }

    public @NotNull Map<Company, List<Product>> getAllCompaniesWithProducts() {
        final var map = new HashMap<Company, List<Product>>();

        final var companies = companyDAO.all();

        companies.forEach(company -> {
            map.put(company, new ArrayList<>());
            productDAO.getProductsByCompanyId(company.getId()).forEach(product -> map.get(company).add(product));
        });

        return map;
    }
}
