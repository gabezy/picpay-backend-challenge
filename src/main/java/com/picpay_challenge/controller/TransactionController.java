package com.picpay_challenge.controller;

import com.picpay_challenge.domain.transaction.Transaction;
import com.picpay_challenge.domain.user.User;
import com.picpay_challenge.dto.CreateTransactionDTO;
import com.picpay_challenge.dto.DetailsTransactionDTO;
import com.picpay_challenge.service.NotificationService;
import com.picpay_challenge.service.TransactionService;
import com.picpay_challenge.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsTransactionDTO> createTransaction(@RequestBody CreateTransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(new DetailsTransactionDTO(newTransaction), HttpStatus.CREATED);

    }
}
