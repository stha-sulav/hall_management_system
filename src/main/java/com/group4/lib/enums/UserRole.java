package com.group4.lib.enums;

public enum UserRole {
    Admin("admin"),
    Staff("staff"),
    Customer("customer");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
