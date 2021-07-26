package payment.system.paymentGateway5;

import payment.system.EPaymentMethod5;
import payment.system.model5.IPaymentDetails5;
import payment.system.processor5.IPaymentProcessor5;

public interface IPaymentGateway5 {

    IPaymentGateway5 withPaymentMethod5(EPaymentMethod5 ePaymentMethod5);

    IPaymentProcessor5 withPaymentDetails5(IPaymentDetails5 iPaymentDetails5);
}
