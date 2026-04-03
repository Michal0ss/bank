package com.bankproject.bank.controller;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.repository.AccountsRepository;
import com.bankproject.bank.repository.CustomersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    private final AccountsRepository accountsRepository;

    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @PostMapping("/dashboard")
    public String goToDetails(@RequestParam int accountId, @RequestParam String email,Model model){
        Accounts account = accountsRepository.findByAccountId(accountId);

        model.addAttribute("account",account);
        model.addAttribute("email",email);
        return "details";
    }
}
