package DemoApplication.paymentGateway;

import DemoApplication.EPaymentMethod;
import DemoApplication.model.IPaymentDetails;
import DemoApplication.processor.IPaymentProcessor;

public interface IPaymentGateway {
    IPaymentGateway withPaymentMethod(EPaymentMethod _ePaymentMethod);

    IPaymentProcessor withPaymentDetails(IPaymentDetails _paymentDetails);

}

