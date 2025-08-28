package com.entity;

public class BankAccount {

    private long accNumber;
    private String accHolderName;
    private double balance;

    public BankAccount (long accNumber, String accHolderName, double balance) {
        this.accNumber = accNumber;
        this.accHolderName = accHolderName;
        this.balance = balance;
    }

    public long getAccNumber () {
        return accNumber;
    }

    public void setAccNumber (long accNumber) {
        this.accNumber = accNumber;
    }

    public String getAccHolderName () {
        return accHolderName;
    }

    public void setAccHolderName (String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public double getBalance () {
        return balance;
    }

    public void setBalance (double balance) {
        this.balance = balance;
    }
}
