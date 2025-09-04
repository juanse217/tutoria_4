package com.sebastian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.sebastian.exceptions.AccountNotFoundException;
import com.sebastian.exceptions.NotEnoughFundsException;
import com.sebastian.model.Account;

public class Atm {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Atm.class);
    private List<Account> accounts;

    public Atm() {
        logger.info("Service layer started");
        accounts = new ArrayList<>();
    }

    public void createAccount(Account account){
        logger.info("creating new account");
        accounts.add(account);
    }

    private Optional<Account> findAccount(String accNumber) {
        logger.info("Looking for account {}", accNumber);
        Optional<Account> accountReturn = accounts.stream().filter(x -> x.getAccountNumber().equals(accNumber)).findFirst();
        logger.debug("Result of account found{}", accountReturn.isPresent());
        return accountReturn; 
    }

    private Account requireAccount(String accNumber) throws AccountNotFoundException{
        return findAccount(accNumber).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public void addBalance(String accountNumber, double balance) throws AccountNotFoundException {
        if(balance <= 0){
            throw new IllegalArgumentException("The balance can't be 0 or less");
        }
        Account acc = requireAccount(accountNumber);
        acc.addBalance(balance);
        logger.info("deposit.ok account{} amount{} newBalance{}", acc.getAccountNumber(), balance, acc.getBalance());
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException {
        Account acc = requireAccount(accountNumber);

        if(acc.withdraw(amount)){
            logger.info("withdraw.ok account{} amount{} newBalance{}", acc.getAccountNumber(), amount, acc.getBalance());
        }else{
            logger.warn("Not enough balance for {}", acc.getAccountNumber());
            throw new NotEnoughFundsException("not enough balance");
        }
    }

    public void showAccountInformation(String accNumber) throws AccountNotFoundException{
        logger.info("showing all information");
        System.out.println(requireAccount(accNumber));
    }
}
