package com.sebastian.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Account {
    private String accountNumber; 
    private double balance; 
    private String ownerName;
    private static Logger logger = (Logger) LogManager.getLogger(Account.class);
    
    public Account(String accountNumber, double balance, String ownerName) {
        logger.info("Creating new account {}", ownerName );
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.ownerName = ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    } 

    public String getOwnerName() {
        return ownerName;
    }

    public void addBalance(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
            return true; 
        }

        return false; 
    }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", ownerName=" + ownerName + "]";
    }
}
