package com.bankproject.bank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "branches")
public class Branches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Accounts> accounts;

    private String name;
    private String city;
    private String address;

    public Branches(Long branchId, List<Accounts> accounts, String name, String city, String address) {
        this.branchId = branchId;
        this.accounts = accounts;
        this.name = name;
        this.city = city;
        this.address = address;
    }
    public Branches() {}

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Branches{" +
                "branchId=" + branchId +
                ", accounts=" + accounts +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
