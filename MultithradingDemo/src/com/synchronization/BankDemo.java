package com.synchronization;

public class BankDemo {

    public static void main (String[] args) throws InterruptedException {
        Bank b=new  Bank();

        Thread t1=new Thread(()->b.deposit(1000));
        Thread t2=new Thread(()->b.withdraw(1500));
        Thread t3=new Thread(()->b.withdraw(500));
        Thread t4=new Thread(()->b.deposit(1000));

        t1.start();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        t4.start();
        t4.join();

        System.out.println("Your current balance is Rs. "+Bank.getBalance());




    }
}
