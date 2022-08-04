package com.jiyong.commerce.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Seller extends User{
    private String businessRegistrationNumber;
}
