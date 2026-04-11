package com.bankproject.bank.controller;

import com.bankproject.bank.entity.*;
import com.bankproject.bank.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public String dashboard(@RequestParam int accountId){
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

    @PostMapping("/modifyCardStatus/{cardId}/{state}")
    public String freeze(@PathVariable Long cardId, @PathVariable boolean state, @RequestParam Long accountId){
        if (state)
            cardsRepository.unFreezeCard(cardId);
        else
            cardsRepository.freezeCard(cardId);

        return "redirect:/details/" + accountId;
    }

    @PostMapping("/newCard")
    public String newCard(@RequestParam Long accountId, @RequestParam String cardType){
        cardsRepository.newCard(accountId, cardType);
        return "redirect:/details/" + accountId;
    }

    @PostMapping("/deleteCard/{cardId}")
    public String deleteCard(@PathVariable Long cardId, @RequestParam Long accountId){
        cardsRepository.deleteCard(cardId);
        return "redirect:/details/" + accountId;
    }

    @PostMapping("/transaction")
    public String newTransaction(@RequestParam Long accountId, @RequestParam String targetNumber ,@RequestParam BigDecimal amount, RedirectAttributes redirectAttributes){
        Accounts account = accountsRepository.findByAccountId(accountId);
        try {
            accountsRepository.transaction(account.getAccountNumber().trim(), targetNumber.trim(), amount);
        }
        catch (Exception e){
            String m = e.getMessage();
            m = m.split("ERROR:")[1].split("!")[0];
            redirectAttributes.addFlashAttribute("error", m);
        }

        return "redirect:/details/" + accountId;
    }

    private String generateUniqueAccountNumber() {
        String number;
        do {
            number = "PL" + String.format("%026d", ThreadLocalRandom.current().nextLong(1_000_000_000_000_000L));
        } while (accountsRepository.existsByAccountNumber(number));
        return number;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @PostMapping("/accounts/new")
    public String createAccount(@RequestParam String accountType,@RequestParam Long branchId ,HttpSession session, RedirectAttributes redirectAttributes){
        Long customerId = (Long) session.getAttribute("userId");
        if  (customerId == null) {return "redirect:/login";}

        Customers customer = customersRepository.findByCustomerId(customerId);
        if (customer == null) {return "redirect:/login";}

        Branches branch = entityManager.getReference(Branches.class, branchId);
        String generatedNumber = generateUniqueAccountNumber();

        BigDecimal balance = BigDecimal.valueOf(getRandomNumber(250, 1000000));

        Accounts account = new Accounts();
        account.setBranch(branch);
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setBalance(balance);
        account.setCreatedAt(LocalDateTime.now());
        account.setAccountNumber(generatedNumber);

        accountsRepository.save(account);
        redirectAttributes.addFlashAttribute("success", "Nowe konto zostało utworzone: " + generatedNumber);
        return "redirect:/dashboard";
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
