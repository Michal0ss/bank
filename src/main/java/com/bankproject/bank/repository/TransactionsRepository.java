package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByFromAccountOrToAccountOrderByCreatedAtDesc(Accounts fromId, Accounts toId);
}