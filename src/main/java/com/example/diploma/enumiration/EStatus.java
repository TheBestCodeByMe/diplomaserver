package com.example.diploma.enumiration;

import java.util.Arrays;
import java.util.Objects;

public enum EStatus {
    ACTIVE(1L, "ACTIVE"),
    CLOSED(2L, "CLOSED"),
    BANNED(3L, "BANNED");

    private final Long id;
    private final String name;

    EStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getName(Long id) {
        return Arrays.stream(EStatus.values()).filter(it -> Objects.equals(id, it.getId())).findFirst().get().getName();
    }

    public static Long getId(String name) {
        return Arrays.stream(EStatus.values()).filter(it -> Objects.equals(name, it.getName())).findFirst().get().getId();
    }
}