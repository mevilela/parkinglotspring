package zely.parkinglotspring.repository.payment;

import org.springframework.data.repository.CrudRepository;

import zely.parkinglotspring.model.payment.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
