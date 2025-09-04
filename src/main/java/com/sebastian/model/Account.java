package com.sebastian.model;


public class Account {
    private String accountNumber; 
    private double balance; 
    private String ownerName;
    
    public Account(String accountNumber, double balance, String ownerName) {
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
        if(amount <= balance && amount > 0){
            balance -= amount;
            return true; 
        }

        return false; 
    }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
    }
}
