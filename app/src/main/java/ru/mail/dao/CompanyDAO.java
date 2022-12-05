package ru.mail.dao;

import org.jetbrains.annotations.NotNull;
import org.jooq.Record;
import ru.mail.Company;
import ru.mail.common.DSLContextHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.COMPANY;

public class CompanyDAO {
    public @NotNull Company getByName(@NotNull String name) {
        try {
            var context = DSLContextHelper.getContext();
            var result = context
                    .select()
                    .from(COMPANY)
                    .where(COMPANY.NAME.eq(name))
                    .fetch();

            for (Record record : result) {
                int id = record.getValue(COMPANY.COMPANY_ID);
                String companyName = record.getValue(COMPANY.NAME);
                return new Company(id, companyName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalStateException("Record with name " + name + "not found");
    }

    public @NotNull List<Company> all() {
        final var list = new ArrayList<Company>();

        try {
            var context = DSLContextHelper.getContext();
            var result = context
                    .select()
                    .from(COMPANY)
                    .fetch();

            result.forEach(record -> {
                int id = record.getValue(COMPANY.COMPANY_ID);
                String companyName = record.getValue(COMPANY.NAME);
                list.add(new Company(id, companyName));
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void save(String companyName) {
        try {
            var context = DSLContextHelper.getContext();
            context
                    .insertInto(COMPANY, COMPANY.NAME)
                    .values(companyName)
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
