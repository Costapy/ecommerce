package com.costa.ecommerce.domain.users;

public enum UsersRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UsersRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
