package com.bankproject.bank.controller;

import com.bankproject.bank.entity.Customers;
import com.bankproject.bank.repository.CustomersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class CustomersController {
    private final CustomersRepository customersRepository;
    public CustomersController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Customers customer = customersRepository.findByEmail(email);

        if (customer == null) {
            model.addAttribute("error","User does not exist");
            return "login";
        }

        if(!customer.getPassword().equals(password)) {
            model.addAttribute("error","Invalid password");
            return "login";
        }

        session.setAttribute("userId",customer.getCustomerId());
        return "redirect:/dashboard";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute Customers customer, Model model) {
        if (customersRepository.existsByEmail(customer.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }
        customer.setCreatedAt(LocalDateTime.now());
        customersRepository.save(customer);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
