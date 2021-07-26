package DemoApplication.paymentGateway;

import DemoApplication.EPaymentMethod;
import DemoApplication.model.IPaymentDetails;
import DemoApplication.processor.IPaymentProcessor;

public class PaymentGateway implements IPaymentGateway {

    IPaymentGateway currentPaymentGateway;//first contract point


    @Override
    public IPaymentGateway withPaymentMethod(EPaymentMethod _ePaymentMethod) {

        switch (_ePaymentMethod) {
            case BKASH:
                currentPaymentGateway = new BkashPaymentGateway();
                return currentPaymentGateway;
            case AMEX:
                currentPaymentGateway = new AmexPaymentGateway();
                return currentPaymentGateway;
            case VISA:
                return currentPaymentGateway = new VisaPaymentGateway();
            case MASTER_CARD:
                return currentPaymentGateway = new MasterCardPaymentGateway();
            case PAYPAL:
                return currentPaymentGateway = new PaypalPaymentGateway();
            default:
                throw new RuntimeException("Invalid Payment Gateway");
        }

    }

    @Override
    public IPaymentProcessor withPaymentDetails(IPaymentDetails _paymentDetails) {

        if(currentPaymentGateway!=null){
            return this.currentPaymentGateway.withPaymentDetails(_paymentDetails);
        }else {
            throw new RuntimeException("Invalid Payment Details");
        }

    }
}