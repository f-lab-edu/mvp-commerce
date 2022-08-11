package com.jiyong.commerce.member.domain;

import java.util.Arrays;

public enum Role {

    ADMIN(1), SELLER(2), USER(3);
    private final int code;

    private Role(int code) {
        this.code = code;
    }


    public static Role ofCode(int code) {
        Role role = Arrays.stream(Role.values()).filter(i -> i.code == code).findAny().orElse(null);
        return role;
    }
}
