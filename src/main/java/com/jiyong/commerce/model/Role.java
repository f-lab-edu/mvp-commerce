package com.jiyong.commerce.model;

public enum Role {
    ADMIN(1),SELLER(2),USER(3);
    private final int code;
    Role(int code) {
        this.code=code;
    }
}
