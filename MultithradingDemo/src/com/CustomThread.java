package com;

import java.util.concurrent.CountDownLatch;

public class CustomThread implements  Runnable{

    String name;
    CountDownLatch countDownLatch;

    public CustomThread (String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;

        new Thread(this);
    }

    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + ": " + i);
            countDownLatch.countDown();
        }
    }
}
