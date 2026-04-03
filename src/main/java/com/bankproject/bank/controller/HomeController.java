package com.bankproject.bank.controller;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Customers;
import com.bankproject.bank.repository.AccountsRepository;
import com.bankproject.bank.repository.CustomersRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final CustomersRepository customersRepository;
    private final AccountsRepository accountsRepository;

    public HomeController(CustomersRepository customersRepository, AccountsRepository accountsRepository) {
        this.customersRepository = customersRepository;
        this.accountsRepository = accountsRepository;
    }
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
