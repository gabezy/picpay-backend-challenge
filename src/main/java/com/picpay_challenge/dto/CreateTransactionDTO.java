package com.picpay_challenge.dto;


import java.math.BigDecimal;

public record CreateTransactionDTO(
        Long senderId,
        Long receiverId,
        BigDecimal amount
) {
}
