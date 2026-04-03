package com.bankproject.bank.controller;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Cards;
import com.bankproject.bank.entity.Customers;
import com.bankproject.bank.entity.Transactions;
import com.bankproject.bank.repository.AccountsRepository;
import com.bankproject.bank.repository.CardsRepository;
import com.bankproject.bank.repository.CustomersRepository;
import com.bankproject.bank.repository.TransactionsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountController {
    private final AccountsRepository accountsRepository;
    private final CustomersRepository customersRepository;
    private final TransactionsRepository transactionsRepository;
    private final CardsRepository cardsRepository;

    public AccountController(AccountsRepository accountsRepository, CustomersRepository customersRepository, TransactionsRepository transactionsRepository, CardsRepository cardsRepository) {
        this.accountsRepository = accountsRepository;
        this.customersRepository = customersRepository;
        this.transactionsRepository = transactionsRepository;
        this.cardsRepository = cardsRepository;
    }

    @PostMapping("/dashboard")
    public String dashboard(@RequestParam int accountId, @RequestParam String email,Model model){
        return "redirect:/details/" + accountId;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){
        Long customerId =  (Long) session.getAttribute("userId");
        Customers customer = customersRepository.findByCustomerId(customerId);
        if (customer == null) {return "redirect:/login";}

        String email = customer.getEmail();
        model.addAttribute("customer", customer);
        model.addAttribute("email", email);
        return "dashboard";
    }

    @GetMapping("/details/{accountId}")
    public String details(HttpSession session,Model model, @PathVariable Long accountId){
        Accounts account = accountsRepository.findByAccountId(accountId);
        Customers customer = account.getCustomer();

        if (!customer.getCustomerId().equals(session.getAttribute("userId"))) {return "redirect:/login";}

        List<Transactions> transactions = transactionsRepository.findByFromAccountOrToAccountOrderByCreatedAtDesc(account, account);
        List<Cards> cards = cardsRepository.findByAccount(account);

        model.addAttribute("account",account);
        model.addAttribute("email",customer.getEmail());
        model.addAttribute("transactions",transactions);
        model.addAttribute("cards",cards);
        return "details";
    }
}
