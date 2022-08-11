package com.jiyong.commerce.member.domain;

import com.jiyong.commerce.member.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(callSuper = true)
public class Seller extends User {
    private String businessRegistrationNumber;

    @Builder
    public Seller(String id, String password, String userType, String name, String email, String address, String phoneNumber, String businessRegistrationNumber) {
        super(id, password, userType, name, email, address, phoneNumber);
        this.businessRegistrationNumber = businessRegistrationNumber;
    }
}
