package com.picpay_challenge.dto;

import com.picpay_challenge.domain.user.UserType;

import java.math.BigDecimal;

public record CreateUserDTO (
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String  email,
        String password,
        UserType type

) {
}
