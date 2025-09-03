package com.sebastian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.sebastian.exceptions.AccountNotFoundException;
import com.sebastian.model.Account;

public class Atm {
    private static final Logger logger = (Logger) LogManager.getLogger(Atm.class);
    private List<Account> accounts;

    public Atm() {
        accounts = new ArrayList<>();
    }

    private Optional<Account> findAccount(String accNumber) {
       return accounts.stream().filter(x -> x.getAccountNumber().equals(accNumber)).findFirst();
    }

    public void addBalance(String accountNumber, double balance) throws AccountNotFoundException {
        Optional<Account> acc = findAccount(accountNumber);
        
        if(acc.isEmpty()){
            throw new AccountNotFoundException("The account wasn't found to add balance");
        }else{
            acc.get().addBalance(balance);
            System.out.println("Succesful adding");
        }
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException {
        Optional<Account> acc = findAccount(accountNumber);

        if(acc.isEmpty()){
            throw new AccountNotFoundException("The account wasn't found for the withdraw");
        }else{
            if(acc.get().withdraw(amount)){
                System.out.println("Succesful withdraw");
            }else{
                System.out.println("Not enough balance");
            }
        }
    }
}
