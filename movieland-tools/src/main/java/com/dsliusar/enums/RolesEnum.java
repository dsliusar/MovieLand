package com.dsliusar.enums;

public enum RolesEnum {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest");

    RolesEnum(String s) {
        this.s = s;
    }

    private String s;
}
