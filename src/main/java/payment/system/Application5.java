package payment.system;

import payment.system.model5.BkashPaymentDetails5;
import payment.system.model5.IPaymentDetails5;

import java.util.Scanner;

public class Application5 extends AbstractPaymentProcessor5{
    public static void main(String[] args) {
        Application5 application5 = new Application5();
        application5.run5();

    }

    @Override
    void run5() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select payment method; ");
        System.out.println("1 = Bkash");
        System.out.println("2 = Master Card");
        System.out.println("3 = Visa");
        System.out.println("4 = AMEX");
        System.out.println("5 = Paypal");
        int i5 = sc.nextInt();

        IPaymentDetails5 iPaymentDetails5 = null;

        switch (i5){
            case 1:
                System.out.println("Enter form card no");
                String fromCardNo5 = sc.next();
                System.out.println("Enter to card no");
                String toCardNo5 = sc.next();
                System.out.println("Enter your pinn");
                String pin5 = sc.next();

                iPaymentDetails5 = new BkashPaymentDetails5(fromCardNo5,toCardNo5,pin5);
            default:
                System.out.println("not set yet");
        }

        this.setPaymentDetails5(iPaymentDetails5);
        super.process5(i5);



    }
}
