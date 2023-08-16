package com.picpay_challenge.repository;

import com.picpay_challenge.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
