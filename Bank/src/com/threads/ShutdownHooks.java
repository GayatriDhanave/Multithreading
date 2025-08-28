package com.threads;

public class ShutdownHooks extends  Thread {
    @Override
    public void run () {
        System.out.println("Shuting down banking application!");
    }
}
