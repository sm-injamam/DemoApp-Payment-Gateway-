package DemoApplication;

import DemoApplication.model.IPaymentDetails;
import DemoApplication.paymentGateway.PaymentGateway;
import DemoApplication.processor.IPaymentProcessor;

public abstract class AbstractPaymentProcessor{
    IPaymentDetails iPaymentDetails;

    public void setPaymentDetails(IPaymentDetails paymentDetails) {
        this.iPaymentDetails = paymentDetails;
    }
    protected void process(int i) {
        EPaymentMethod ePaymentMethod = EPaymentMethod.getValue(i);
        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.withPaymentMethod(ePaymentMethod).withPaymentDetails(iPaymentDetails).process();
    }

    abstract void run();
}

