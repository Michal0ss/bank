package com.bankproject.bank.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "cards")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cardId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    private Accounts account;

    @Column(unique = true)
    private int cardNumber;

    @DateTimeFormat(pattern = "MM/yy")
    private Date expiryDate;

    private String cvv;

    private String cardType;

    public Cards(String cardType, String cvv, Date expiryDate, int cardNumber, Accounts accountId, String cardId) {
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardNumber = cardNumber;
        this.account = accountId;
        this.cardId = cardId;
    }

    public Cards() {    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Accounts getAccountId() {
        return account;
    }

    public void setAccountId(Accounts accountId) {
        this.account = accountId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "cardId=" + cardId +
                ", accountId=" + account +
                ", cardNumber=" + cardNumber +
                ", expiryDate=" + expiryDate +
                ", cvv=" + cvv +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
