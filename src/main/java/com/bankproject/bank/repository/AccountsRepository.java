package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByAccountId(Long id);
}
