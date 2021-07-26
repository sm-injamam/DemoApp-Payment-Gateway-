package DemoApplication.processor;

import DemoApplication.model.IPaymentDetails;

public class MasterCardPaymentProcessor implements IPaymentProcessor {
    private final IPaymentDetails paymentDetails;

    public MasterCardPaymentProcessor(IPaymentDetails paymentDetails) {
        this.paymentDetails =paymentDetails;

    }

    @Override
    public void process() {
        System.out.println("Master Card Payment Successful");
        System.out.println(paymentDetails.toString());
    }
}
