package ru.mail;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Company {
    public final int id;
    public final @NotNull String name;
}
