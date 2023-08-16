package com.picpay_challenge.dto;

import com.picpay_challenge.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DetailsTransactionDTO(Long id, BigDecimal amount, Long receiverId, Long senderId, LocalDateTime timestamp) {
    public DetailsTransactionDTO(Transaction data) {
        this(data.getId(), data.getAmount(), data.getReceiver().getId(), data.getSender().getId(), data.getTimestamp());
    }
}
