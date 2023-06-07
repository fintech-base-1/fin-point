package com.fp.finpoint.domain.member.entity;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_SELLER("ROLE_SELLER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
