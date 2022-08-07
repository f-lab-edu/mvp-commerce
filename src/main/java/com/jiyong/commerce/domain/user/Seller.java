package com.jiyong.commerce.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Seller extends User{
    private String businessRegistrationNumber;

    @Builder
    public Seller(String id, String password, String userType, String name, String email, String address, String phoneNumber, String businessRegistrationNumber) {
        super(id, password, userType, name, email, address, phoneNumber);
        this.businessRegistrationNumber = businessRegistrationNumber;
    }
}
