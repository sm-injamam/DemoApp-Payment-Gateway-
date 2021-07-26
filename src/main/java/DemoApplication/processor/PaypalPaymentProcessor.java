package DemoApplication.processor;

public class PaypalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void process() {
        System.out.println("Paypal does not support in your region");
    }
}
