import com.service.BankServices;
import com.threads.ShutdownHooks;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main (String[] args) {
        ExecutorService exe= Executors.newFixedThreadPool(3);
        BankServices services = new BankServices();
        Scanner sc=new Scanner(System.in);
        int ch=0;

        Runtime rt=Runtime.getRuntime();
        rt.addShutdownHook(new ShutdownHooks());
        rt.addShutdownHook(new Thread(()-> System.out.println("Application shutdown complete")));

        while (ch!=6){
            System.out.println("1. Create new Account\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Get account details\n6. Exit");
            ch= sc.nextInt();
            switch (ch){
                case 1:
                    System.out.println("Enter name: ");
                    String name=sc.next();
                    System.out.println("Enter initial balance: ");
                    double balance=sc.nextDouble();
                    exe.submit(()->services.addAccount(name,balance));
                    break;
                case 2:
                    System.out.println("Enter Account number: ");
                    long accNumber=sc.nextLong();
                    System.out.println("Enter amount: ");
                    double amount=sc.nextDouble();
                    exe.submit(()->services.addDeposit(accNumber,amount));
                    break;
                case 3:
                    System.out.println("Enter Account Number: ");
                    long accountNumber=sc.nextLong();
                    System.out.println("Enter amount: ");
                    double amount1=sc.nextDouble();
                    exe.submit(()-> services.withdrawal(accountNumber,amount1));
                    break;
                case 4:
                    System.out.println("Enter your(sender's) account number: ");
                    long from=sc.nextLong();
                    System.out.println("Enter receiver's account number: ");
                    long to=sc.nextLong();
                    System.out.println("Enter amount: ");
                    double amount2=sc.nextDouble();
                    exe.submit(()-> services.transfer(from,to,amount2));
                    break;
                case 5:
                    System.out.println("Enter your account number: ");
                    long acc=sc.nextLong();
                    exe.submit(()-> services.getAccountDetails(acc));
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

}