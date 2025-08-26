package com.demo;

public class ThreadTask extends Thread{
    String task;


    public ThreadTask (String task) {
        super(task);
        this.task = task;
    }

    @Override
    public void run () {
        System.out.println("My task is: "+task);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
