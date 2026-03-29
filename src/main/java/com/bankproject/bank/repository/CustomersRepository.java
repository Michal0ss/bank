package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {
    Customers findByEmail(String email);

    boolean existsByEmail(String email);
}
