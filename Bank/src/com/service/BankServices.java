package com.service;

import com.entity.BankAccount;
import com.inmemorycache.Storage;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
            ReentrantLock lock = account.getLock();
            try {
                if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                    lock.lock();
                    try {
                        double newBalance=account.getBalance()+amount;
                        account.setBalance(newBalance);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Amount of Rs. "+amount+" credited successfully! Your "+account.getAccNumber()+ " current" +
                    " balance is Rs. "+account.getBalance());
        }
        else {
            System.out.println("Account Number "+accNumber+" does not exist");
        }
    }

    public synchronized void withdrawal(long accNumber, double amount) {

        BankAccount account = storage.getBankAccount(accNumber);
        if (account != null) {
            ReentrantLock lock = account.getLock();
            try {
                if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    lock.lock();
                    if (account.getBalance() >= amount) {

                        try {
                            account.setBalance(account.getBalance() - amount);
    //                    if we use readwrite-lock then the above stmt would have caused deadlock
    //                    also everytime needed to release read and write lock seperately
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            lock.unlock();
                        }
                        System.out.println("Amount of Rs. " + amount + " debited successfully from " + account.getAccNumber() +
                                "! Your current balance is Rs. " + account.getBalance());
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else  {
            System.out.println("Account Number "+accNumber+" does not exist");
        }
    }

    public  void transfer(long fromAccNumber,long toAccNumber, double amount) {
        synchronized(this){
            BankAccount fromAccount = storage.getBankAccount(fromAccNumber);
            BankAccount toAccount = storage.getBankAccount(toAccNumber);

            if (fromAccount != null && toAccount != null && fromAccount.getAccNumber()!=toAccount.getAccNumber()) {
                ReentrantLock fromAccountLock = fromAccount.getLock();
                ReentrantLock toAccountLock = toAccount.getLock();
                try {
                    if(fromAccountLock.tryLock(1000, TimeUnit.MILLISECONDS) && toAccountLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        if (fromAccount.getBalance() >= amount) {

                            try {
                                fromAccount.setBalance(fromAccount.getBalance() - amount);
                                toAccount.setBalance(toAccount.getBalance() + amount);
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } finally {
                                fromAccountLock.unlock();
                                toAccountLock.unlock();
                            }
                            System.out.println("Amount of Rs. " + amount + " has been debited from " + fromAccount.getAccNumber() +
                                    " and successfully transeferred to " + toAccount.getAccHolderName());
                        } else {
                            System.out.println("Insufficient balance!");
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else  {
                System.out.println("Error processing request! Please try again.");
            }
        }

    }

    public void getAccountDetails (long acc) {
        BankAccount account = storage.getBankAccount(acc);
        if(account!=null){

            ReentrantLock lock = account.getLock();
            try {
                if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                    System.out.println(account);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
