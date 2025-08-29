package com.synchronization;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Bank {

    private static int balance=1000;

    public void deposit(int amt){
        synchronized (this) {
            balance=balance+amt;
            System.out.println("Money deposited: Rs. "+amt+". Your balance is: Rs. "+balance);
        }
    }

    public synchronized  void withdraw(int amt){
        if(balance>=amt){
            balance=balance-amt;
            System.out.println("Money withdrawed: Rs. "+amt+". Your balance is: Rs. "+balance);

        }
        else {
            System.out.println("Insufficient balance");
//            ReadWriteLock rwLock = new ReentrantReadWriteLock();
//            rwLock.
        }
    }

    public static synchronized  int getBalance() {
        return balance;
    }
}
