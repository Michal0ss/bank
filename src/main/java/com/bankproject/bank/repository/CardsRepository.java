package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Cards;
import com.bankproject.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsRepository extends JpaRepository<Cards, Integer> {
    List<Cards> findByAccount(Accounts account);
}
