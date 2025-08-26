package com.synchronization;

public class SynchronizationDemo {

    static int count=0;

//    synchronized method
    public  synchronized void increment() {
        count++;
    }

//    synchronized block
    public void inc(){
        synchronized (this){
            count++;
        }
    }

    public  static void main(String[] args) throws InterruptedException {
        SynchronizationDemo sd = new SynchronizationDemo();
        Thread t1= new  Thread(()-> {
            for (int i =0;i<100;i++) {
                sd.inc();
//                sd.increment();
//                count++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2= new  Thread(()-> {
            for (int i =0;i<100;i++) {
                sd.inc();
//                sd.increment();
//                count++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Count is: "+count);

    }
}
