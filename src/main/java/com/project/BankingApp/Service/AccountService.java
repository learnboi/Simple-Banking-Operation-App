package com.project.BankingApp.Service;

import com.project.BankingApp.Model.Account;
import com.project.BankingApp.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        } else{
            Account account = new Account();
            account.setId(0);
            account.setName(null);
            account.setBalance(0);
            return account;
        }
    }

    public Account updateAccount(Account newAccount, int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if(optional.isPresent()){
            Account oldAccount = optional.get();
            oldAccount.setName(newAccount.getName());
            oldAccount.setBalance(newAccount.getBalance());
            return accountRepository.save(oldAccount);
        }
        return accountRepository.save(newAccount);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if(optional.isPresent()){
            accountRepository.delete(optional.get());
            return "Account deleted successfully!!!";
        }
        return "Account not found!!!";
    }

    public Account deposit(int id, Double amount) {
        Optional<Account> optional = accountRepository.findById(id);
        if(optional.isPresent()){
            Account oldAccount = optional.get();
            oldAccount.setBalance(oldAccount.getBalance() + amount);
            return accountRepository.save(oldAccount);
        } else{
            Account account = new Account();
            account.setId(0);
            account.setName(null);
            account.setBalance(0);
            return account;
        }
    }

    public Account withdraw(int id, Double amount) {
        Optional<Account> optional = accountRepository.findById(id);
        if(optional.isPresent()){
            Account oldAccount = optional.get();
            oldAccount.setBalance(oldAccount.getBalance() - amount);
            return accountRepository.save(oldAccount);
        }  else{
            Account account = new Account();
            account.setId(0);
            account.setName(null);
            account.setBalance(0);
            return account;
        }
    }
}
