package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    @Query(value = """
    SELECT
        t.transaction_id,
        t.amount,
        t.created_at,
        CASE
            WHEN t.to_account_id = :accountId THEN 'IN'
            ELSE 'OUT'
        END AS direction
    FROM transactions t
    WHERE (t.from_account_id = :accountId OR t.to_account_id = :accountId)
      AND t.created_at BETWEEN :dateFrom AND :dateTo
      AND (
            :direction = 'ALL'
            OR (:direction = 'IN' AND t.to_account_id = :accountId)
            OR (:direction = 'OUT' AND t.from_account_id = :accountId)
      )
    ORDER BY t.created_at DESC
    """, nativeQuery = true)
    List<Object[]> generateReport(Long accountId, LocalDateTime dateFrom, LocalDateTime dateTo, String direction);

    List<Transactions> findByFromAccountOrToAccountOrderByCreatedAtDesc(Accounts fromId, Accounts toId);
}