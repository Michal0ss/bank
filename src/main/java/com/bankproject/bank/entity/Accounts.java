package com.bankproject.bank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @OneToMany(targetEntity = Cards.class, mappedBy = "account", fetch = FetchType.LAZY)
    private List<Cards> cards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branches branch;


    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private LocalDateTime createdAt;


}
