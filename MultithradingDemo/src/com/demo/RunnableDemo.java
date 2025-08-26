package com.demo;

public class RunnableDemo implements  Runnable {


    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            System.out.println("Count: "+i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
//                System.out.println("Thread interupted");
            }
        }
    }
}
