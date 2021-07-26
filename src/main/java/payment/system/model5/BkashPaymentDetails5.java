package payment.system.model5;

public class BkashPaymentDetails5 implements IPaymentDetails5{
    String fromMobileNo5,toMobileNo5,pin5;

    public BkashPaymentDetails5(String fromMobileNo5,String toMobileNo5,String pin5){
    this.fromMobileNo5 = fromMobileNo5;
    this.toMobileNo5 = toMobileNo5;
    this.pin5 = pin5;
    }
}
