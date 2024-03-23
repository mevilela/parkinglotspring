package zely.parkinglotspring.factory;

import org.springframework.stereotype.Component;
import zely.parkinglotspring.repository.payment.PaymentRepository;
import zely.parkinglotspring.service.payment.CashPaymentService;
import zely.parkinglotspring.service.payment.CreditCardPaymentService;
import zely.parkinglotspring.service.payment.PaymentService;

@Component
public class PaymentServiceFactory {

    public PaymentService getPaymentService(String paymentMethod){
        return switch (paymentMethod) {
            case "cash" -> new CashPaymentService();
            case "creditcard" -> new CreditCardPaymentService();
            default -> throw new IllegalArgumentException("invalid payment method");
        };
    }
}
