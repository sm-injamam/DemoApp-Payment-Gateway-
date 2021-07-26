package payment.system;

import payment.system.model5.IPaymentDetails5;
import payment.system.paymentGateway5.IPaymentGateway5;
import payment.system.paymentGateway5.PaymentGateway5;

public abstract class AbstractPaymentProcessor5 {
    IPaymentDetails5 iPaymentDetails5;

    public void setPaymentDetails5(IPaymentDetails5 iPaymentDetails5){
        this.iPaymentDetails5 = iPaymentDetails5;
    }

    protected void process5(int i5){
        EPaymentMethod5 ePaymentMethod5 = EPaymentMethod5.getValue5(i5);
        IPaymentGateway5 iPaymentGateway5 = new PaymentGateway5();
    }

    abstract void run5();
}
