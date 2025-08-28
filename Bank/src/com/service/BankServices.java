package com.service;

import com.entity.BankAccount;
import com.inmemorycache.Storage;

import java.util.Random;

public class BankServices {

    Storage storage=new Storage();

    public void addAccount(String accName, double initialBalance){
        Random rand = new Random();
        int accNumber = rand.nextInt(99000000)+ 1000000;
        BankAccount account=new BankAccount(accNumber, accName, initialBalance);
        storage.save(account);
        System.out.println("Account Created Successfully! Your Account Number is "+account.getAccNumber());
    }

    public synchronized void addDeposit(long accNumber,double amount) {
        BankAccount account = storage.getBankAccount(accNumber);
        if(account!=null){
           double newBalance=account.getBalance()+amount;
           account.setBalance(newBalance);
            System.out.println("Amount of Rs. "+amount+" crebited successfully! Your current balance is Rs. "+account.getBalance());
        }
        else {
            System.out.println("Account Number "+accNumber+" does not exist");
        }
    }

    public synchronized void withdrawal(long accNumber, double amount) {
        BankAccount account = storage.getBankAccount(accNumber);
        if (account != null) {
            if(account.getBalance()>=amount){
                account.setBalance(account.getBalance()-amount);
                System.out.println("Amount of Rs. "+amount+" debited successfully! Your current balance is Rs. "+account.getBalance());
            }
            else  {
                System.out.println("Insufficient balance!");
            }
        }
        else  {
            System.out.println("Account Number "+accNumber+" does not exist");
        }
    }

    public synchronized void transfer(long fromAccNumber,long toAccNumber, double amount) {
        BankAccount fromAccount = storage.getBankAccount(fromAccNumber);
        BankAccount toAccount = storage.getBankAccount(toAccNumber);
        if (fromAccount != null && toAccount != null) {
            if(fromAccount.getBalance()>=amount){
                fromAccount.setBalance(fromAccount.getBalance()-amount);
                toAccount.setBalance(toAccount.getBalance()+amount);
                System.out.println("Amount of Rs. "+amount+ " has been debited and successfully transeferred to "+toAccount.getAccHolderName());
            }
            else  {
                System.out.println("Insufficient balance!");
            }
        }
        else  {
            System.out.println("Account Number does not exist");
        }
    }

}
