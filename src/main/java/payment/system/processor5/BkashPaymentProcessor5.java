package payment.system.processor5;

import payment.system.model5.IPaymentDetails5;

public class BkashPaymentProcessor5 implements  IPaymentProcessor5{

    private final IPaymentDetails5 iPaymentDetails5;

    public BkashPaymentProcessor5(IPaymentDetails5 iPaymentDetails5) {
       this.iPaymentDetails5 = iPaymentDetails5; //payment - ipayment
    }

    @Override
    public void process5() {
        System.out.println("Bkash payment Successfull");

    }
}
