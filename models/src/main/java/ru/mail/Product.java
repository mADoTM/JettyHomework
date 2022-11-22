package ru.mail;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Product {
    private final int id;
    private final @NotNull String name;
    private final int companyId;
    private final int count;
}
