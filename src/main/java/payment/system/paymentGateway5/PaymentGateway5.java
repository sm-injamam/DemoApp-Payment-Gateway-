package payment.system.paymentGateway5;

import payment.system.EPaymentMethod5;
import payment.system.model5.IPaymentDetails5;
import payment.system.processor5.IPaymentProcessor5;

public class PaymentGateway5 implements IPaymentGateway5 {

    IPaymentGateway5 currentPaymentGateway5;
    @Override
    public IPaymentGateway5 withPaymentMethod5(EPaymentMethod5 ePaymentMethod5) {
        switch (ePaymentMethod5){
            case BKASH_5:
                return currentPaymentGateway5 = new BkashPaymentGateway5();
            default:
                throw new RuntimeException("Invalid payment gateway");
        }
    }

    @Override
    public IPaymentProcessor5 withPaymentDetails5(IPaymentDetails5 iPaymentDetails5) {
        if (currentPaymentGateway5 != null){
            return this.currentPaymentGateway5.withPaymentDetails5(iPaymentDetails5);
        }
        else
            throw new RuntimeException("Invalid");
    }
}
