package DemoApplication.model;

public class VisaPaymentDetails implements IPaymentDetails {
    String fromCardNo;
    String toAccountNo;
    String expirationDate;
    String cvv;

    public String getFromCardNo() {
        return fromCardNo;
    }

    public void setFromCardNo(String fromCardNo) {
        this.fromCardNo = fromCardNo;
    }

    public String getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(String toAccountNo) {
        this.toAccountNo = toAccountNo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "VisaPaymentDetails{" +
                "fromCardNo='" + fromCardNo + '\'' +
                ", toAccountNo='" + toAccountNo + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }

    public VisaPaymentDetails(String fromCardNo, String toAccountNo, String expirationDate, String cvv) {
        this.fromCardNo = fromCardNo;
        this.toAccountNo = toAccountNo;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}
