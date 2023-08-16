package com.picpay_challenge.dto;

import com.picpay_challenge.domain.user.User;
import com.picpay_challenge.domain.user.UserType;

import java.math.BigDecimal;

public record DetailsUserDTO(
        Long id,
        String firstName,
        String lastName,
        String document,
        String email,
        BigDecimal balance,
        UserType userType
) {
    public DetailsUserDTO(User user) {
        this(
                user.getId(), user.getFirstName(), user.getLastName(), user.getDocument(),
                user.getEmail(), user.getBalance(), user.getUserType()
        );
    }
}
