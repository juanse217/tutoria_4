package com.sebastian;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.sebastian.exceptions.AccountNotFoundException;
import com.sebastian.model.Account;
import com.sebastian.service.Atm;

public class Main {
    private static final Logger logger = (Logger) LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        Atm atm = new Atm();
        Account acc1 = new Account("1", 100000, "sebastian");
        Account acc2 = new Account("2", 50, "John");
        Account acc3 = new Account("3", 100, "Juan");

        atm.createAccount(acc1);
        atm.createAccount(acc2);
        atm.createAccount(acc3);
        
        try {
            atm.addBalance("1", 40);
        } catch (AccountNotFoundException e) {
            logger.warn("Account not found to add balance");
        }

        try {
            atm.addBalance("2", 40);
        } catch (AccountNotFoundException e) {
            logger.warn("Account not found to add balance");
        }

        try {
            atm.addBalance("4", 40);
        } catch (AccountNotFoundException e) {
            logger.warn("Account not found to add balance");
        }


        try {
            atm.withdraw("1", 40);
        } catch (AccountNotFoundException e) {
            logger.warn("Account not found to withdraw");
        }

        try {
            atm.withdraw("2", 100);
        } catch (AccountNotFoundException e) {
            logger.warn("Account not found to withdraw");
        }
        
    }
}