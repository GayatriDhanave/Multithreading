package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void main (String[] args) {
        CountDownLatch cd1= new CountDownLatch(5);
        CountDownLatch cd2= new CountDownLatch(5);
        CountDownLatch cd3= new CountDownLatch(5);
        CountDownLatch cd4= new CountDownLatch(5);
        CountDownLatch cd5= new CountDownLatch(5);

        ExecutorService es= Executors.newFixedThreadPool(2);
        es.execute(new CustomThread("Thread-1", cd1));
        es.execute(new CustomThread("Thread-2", cd2));
        es.execute(new CustomThread("Thread-3", cd3));
        es.execute(new CustomThread("Thread-4", cd4));
        es.execute(new CustomThread("Thread-5", cd5));

        try {
            cd1.await();
            cd2.await();
            cd3.await();
            cd4.await();
            cd5.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
