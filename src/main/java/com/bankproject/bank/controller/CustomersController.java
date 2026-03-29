package com.bankproject.bank.controller;

import com.bankproject.bank.entity.Customers;
import com.bankproject.bank.repository.CustomersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomersController {
    private final CustomersRepository customersRepository;
    public CustomersController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Customers customer = customersRepository.findByEmail(email);

        if (customer == null) {return "login";}

        if(!customer.getPassword().equals(password)) {return "login";}

        model.addAttribute("customer", customer);
        return "dashboard";
    }
}
