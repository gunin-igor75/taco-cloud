package ru.gil.tacocloud.model;

public enum Role {

    USER("ROLE_UER"),
    ADMIN("ROLE_ADMIN");

    private final String role;
    Role(String role) {
        this.role = role;
    }
}
