package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    Customers findByEmail(String email);
    Customers findByCustomerId(Long id);

    boolean existsByEmail(String email);
}
