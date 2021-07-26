package DemoApplication;

import DemoApplication.model.*;
//import DemoApplication.model.IPaymentDetails;
//import DemoApplication.model.VisaPaymentDetails;

import java.util.Scanner;



public class Application extends AbstractPaymentProcessor {

    @Override
    void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select payment method:");
        System.out.println("1 = Bkash");
        System.out.println("2 = Master Card");
        System.out.println("3 = Visa");
        System.out.println("4 = AMEX");
        System.out.println("5 = Paypal");
        int i = scanner.nextInt();
        IPaymentDetails paymentDetails; // first contract point

//        if (){} // next modification
        switch (i) {//user selection cases
            case 1:
                System.out.println("Enter fromMobileNo");
                String fromMobileNo = scanner.next();
                System.out.println("Enter toMobileNo");
                String toMobileNo = scanner.next();
                System.out.println("Enter pin");
                String pin = scanner.next();
                paymentDetails = new BkashPaymentDetails(fromMobileNo, toMobileNo, pin);
                break;
            default:
                System.out.println("Enter fromCardNo ");
                String fromCardNo = scanner.next();
                System.out.println("Enter toAccountNo");
                String toAccountNo = scanner.next();
                System.out.println("Enter expirationDate");
                String expirationDate = scanner.next();
                System.out.println("Enter cvv");
                String cvv = scanner.next();
                paymentDetails = new VisaPaymentDetails(fromCardNo, toAccountNo, expirationDate, cvv);
                break;
        }

        this.setPaymentDetails(paymentDetails);
        this.process(i);
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }
}
