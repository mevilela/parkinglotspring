package zely.parkinglotspring.service.payment;

import zely.parkinglotspring.model.payment.Payment;

public interface PaymentService {

    Payment processPayment(Payment payment);
}
