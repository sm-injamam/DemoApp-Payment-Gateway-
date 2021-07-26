package DemoApplication.processor;

public class AmexPaymentProcessor implements IPaymentProcessor {
    @Override
    public void process() {
        System.out.println("Amex is not implemented yet");
    }
}

