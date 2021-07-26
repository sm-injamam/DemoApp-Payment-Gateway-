package DemoApplication.processor;

import DemoApplication.model.IPaymentDetails;

public class BkashPaymentProcessor implements IPaymentProcessor {
    private final IPaymentDetails paymentDetails;

    public BkashPaymentProcessor(IPaymentDetails paymentDetails) {
        this.paymentDetails =paymentDetails;
    }

    @Override
    public void process() {
        System.out.println("Bkash Payment Successful. ");
        System.out.println(paymentDetails.toString());
    }
}
