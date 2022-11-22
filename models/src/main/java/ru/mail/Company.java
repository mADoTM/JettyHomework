package ru.mail;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Company {
    private final int id;
    private final @NotNull String name;
}
