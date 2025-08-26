import com.demo.RunnableDemo;
import com.demo.ThreadTask;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main (String[] args) throws InterruptedException {
        ThreadTask t1=new ThreadTask("Initialization");
        ThreadTask t2=new ThreadTask("Loading");
        ThreadTask t3=new ThreadTask("Cleaning");

        t1.start();
        System.out.println("Thread ID:" + t1.getId());
        System.out.println("Thread Name:" +t1.getName());
        t2.start();
        t3.start();
        t2.setPriority(10);

        RunnableDemo r1=new RunnableDemo();
        RunnableDemo r2=new RunnableDemo();
        RunnableDemo r3=new RunnableDemo();
        Thread t4=new Thread(r1);
        Thread t5=new Thread(r2);
        Thread t6=new Thread(r3);

        Thread t= new Thread(()->{
            int num=2;
            for (int i=1; i<6; i++){
                System.out.println(num*i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t.start();

        t4.start();
        t.join();
        t4.interrupt();
        t5.start();
//        t.join();  // this results in executing t6 after t completes its execution
        t6.start();




    }
}