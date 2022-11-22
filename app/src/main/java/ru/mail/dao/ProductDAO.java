package ru.mail.dao;

import org.jetbrains.annotations.NotNull;
import org.jooq.Record;
import ru.mail.Product;
import ru.mail.common.DSLContextHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.PRODUCT;

public class ProductDAO {
    public List<Product> getProductsByCompanyId(int companyId) {
        final var list = new ArrayList<Product>();

        try {
            var context = DSLContextHelper.getContext();
            var result = context
                    .select()
                    .from(PRODUCT)
                    .where(PRODUCT.COMPANY_ID.eq(companyId))
                    .fetch();

            for (Record record : result) {
                int id = record.getValue(PRODUCT.PRODUCT_ID);
                int dbCompanyId = record.getValue(PRODUCT.COMPANY_ID);
                String name = record.getValue(PRODUCT.NAME);
                int count = record.getValue(PRODUCT.COUNT);
                list.add(new Product(id, name, dbCompanyId, count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void save(@NotNull String productName,
                     int companyId,
                     int count) {
        try {
            var context = DSLContextHelper.getContext();
            context
                    .insertInto(PRODUCT, PRODUCT.NAME, PRODUCT.COMPANY_ID, PRODUCT.COUNT)
                    .values(productName, companyId, count)
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
