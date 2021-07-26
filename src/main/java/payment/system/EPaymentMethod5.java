package payment.system;

import java.util.stream.Stream;

public enum EPaymentMethod5 {

    BKASH_5(1),
    MASTER_CARD(2),
    VISA_5(3),
    AMEX_5(4),
    PAYPAL_5(5);

    int paymentMethodType5;

    EPaymentMethod5(int i5){
        this.paymentMethodType5 = i5;
    }

    static public EPaymentMethod5 getValue5(int i5){
        return Stream.of(EPaymentMethod5.values()).filter(x->x.paymentMethodType5 == i5).findFirst().get();
    }
}
