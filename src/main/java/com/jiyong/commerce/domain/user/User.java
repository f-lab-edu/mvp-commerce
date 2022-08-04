package com.jiyong.commerce.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private String id;
    private String password;
    private String userType;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

}
