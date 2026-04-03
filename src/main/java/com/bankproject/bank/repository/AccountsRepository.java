package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByAccountId(Long id);

    @Procedure(procedureName = "transaction")
    void transaction(@Param("_source_number")String sourceNumber, @Param("_target_number")String targetNumber, @Param("_amount")BigDecimal amount);
}
