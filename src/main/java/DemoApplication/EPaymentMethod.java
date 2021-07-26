package DemoApplication;

import java.util.stream.Stream;

public enum EPaymentMethod {
    BKASH(1),
    MASTER_CARD(2),
    VISA(3),
    AMEX(4),
    PAYPAL(5);

    int paymentMethodType;

    EPaymentMethod(int i) {

        this.paymentMethodType = i;
    }

    static public EPaymentMethod getValue(int i) {
        return Stream.of(EPaymentMethod.values()).filter(x -> x.paymentMethodType == i).findFirst().get();
    }
}
