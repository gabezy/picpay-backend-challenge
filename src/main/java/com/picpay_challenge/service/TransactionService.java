package com.picpay_challenge.service;

import com.picpay_challenge.domain.transaction.Transaction;
import com.picpay_challenge.domain.user.User;
import com.picpay_challenge.dto.CreateTransactionDTO;
import com.picpay_challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    private String authorizationEndPoint = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";


    public Transaction createTransaction(CreateTransactionDTO transaction) throws Exception {
        User receiver = this.userService.findUserById(transaction.receiverId());
        User sender = this.userService.findUserById(transaction.senderId());
        userService.validateTransaction(sender, transaction.amount());
        boolean isAuthorized = authorizeTransaction(sender, transaction.amount());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setReceiver(receiver);
        newTransaction.setSender(sender);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.subtractBalance(transaction.amount());
        receiver.addBalance(transaction.amount());

        this.transactionRepository.save(newTransaction);
        //this.userService.saveUser(sender);
        //this.userService.saveUser(receiver);

        //this.notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        //this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!");
        System.out.println("Notificação enviado para usuária");

        return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal amount) {
       ResponseEntity<Map> authorizationResponse = this.restTemplate.getForEntity(this.authorizationEndPoint, Map.class);
       if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
           String message = (String) authorizationResponse.getBody().get("message");
           return "Autorizado".equalsIgnoreCase(message);
       }
       return false;
    }


}
