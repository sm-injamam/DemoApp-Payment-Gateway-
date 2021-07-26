package payment.system.paymentGateway5;

import payment.system.EPaymentMethod5;
import payment.system.model5.IPaymentDetails5;
import payment.system.processor5.BkashPaymentProcessor5;
import payment.system.processor5.IPaymentProcessor5;

public class BkashPaymentGateway5 implements IPaymentGateway5 {
    @Override
    public IPaymentGateway5 withPaymentMethod5(EPaymentMethod5 ePaymentMethod5) {
        System.out.println("BkashPaymentGateway selected");
        return this;

    }

    @Override
    public IPaymentProcessor5 withPaymentDetails5(IPaymentDetails5 iPaymentDetails5) {
        return new BkashPaymentProcessor5(iPaymentDetails5);
    }


}
