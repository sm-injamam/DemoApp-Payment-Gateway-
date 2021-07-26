package DemoApplication.processor;

public class VisaPaymentProcessor implements IPaymentProcessor {
    @Override
    public void process() {
        System.out.println("Visa Payment Successful");
    }
}
