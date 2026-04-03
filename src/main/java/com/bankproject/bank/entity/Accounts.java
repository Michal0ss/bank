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
    private Long accountId;

    @OneToMany(targetEntity = Cards.class, mappedBy = "account", fetch = FetchType.LAZY)
    private List<Cards> cards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branches branch;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name="balance")
    private BigDecimal balance;

    private String accountType;
    private LocalDateTime createdAt;

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public Customers getCustomer() {
        return customer;
    }

    public Branches getBranch() {
        return branch;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAccountType() {
        return accountType;
    }
}
