package zely.parkinglotspring.service.payment;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.payment.Cash;
import zely.parkinglotspring.model.payment.CreditCard;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.model.payment.PaymentStatus;
import zely.parkinglotspring.repository.payment.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Payment payment) {

        if (payment.getAmount() != payment.getParkingTicket().getAmount()){
            payment.setPaymentStatus(PaymentStatus.FAILED);
        } else {
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
        }

        return paymentRepository.save(payment);
    }
}
