package ru.mail;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Product {
    public final int id;
    public final @NotNull String name;
    public final int companyId;
    public final int count;
}
