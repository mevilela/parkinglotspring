package zely.parkinglotspring.service.payment;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.model.payment.PaymentStatus;
import zely.parkinglotspring.repository.payment.PaymentRepository;

@Service
public class CashPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;

    public CashPaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment processPayment(Payment payment) {

        if (payment.getAmount() != payment.getParkingTicket().getAmount()){
            payment.setPaymentStatus(PaymentStatus.FAILED);
        } else {
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
        }

        return paymentRepository.save(payment);
    }
}
