package com.project.BankingApp.Controller;

import com.project.BankingApp.Model.Account;
import com.project.BankingApp.Repository.AccountRepository;
import com.project.BankingApp.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/account")
@RestController
public class Controller {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}/deposit")
    public Account deposit(@PathVariable int id, @RequestBody Map<String , Double> Map){
        Double amount = Map.get("amount");
        return accountService.deposit(id,amount);
    }

    @PutMapping("{id}/withdraw")
    public Account withdraw(@PathVariable int id, @RequestBody Map<String, Double> Map){
        Double amount = Map.get("amount");
        return accountService.withdraw(id,amount);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id){
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@RequestBody Account newAccount, @PathVariable int id){
        return accountService.updateAccount(newAccount,id);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable int id){
        return accountService.deleteAccount(id);
    }


}
