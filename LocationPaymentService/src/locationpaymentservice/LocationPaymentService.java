package locationpaymentservice;

public interface LocationPaymentService {
	boolean processPayment(String location, double amount);
}
