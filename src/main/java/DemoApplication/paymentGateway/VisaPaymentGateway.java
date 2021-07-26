package DemoApplication.paymentGateway;

import DemoApplication.EPaymentMethod;
import DemoApplication.model.IPaymentDetails;
import DemoApplication.processor.IPaymentProcessor;
import DemoApplication.processor.VisaPaymentProcessor;

public class VisaPaymentGateway implements IPaymentGateway{
    @Override
    public IPaymentGateway withPaymentMethod(EPaymentMethod _ePaymentMethod) {
        System.out.println("VisaPaymentGateway selected");
        return this;

    }

    @Override
    public IPaymentProcessor withPaymentDetails(IPaymentDetails _paymentDetails) {
        return new  VisaPaymentProcessor();
    }
}
