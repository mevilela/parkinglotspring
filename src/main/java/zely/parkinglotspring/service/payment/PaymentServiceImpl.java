//package zely.parkinglotspring.service.payment;
//
//import org.springframework.stereotype.Service;
//import zely.parkinglotspring.dto.PaymentDto;
//import zely.parkinglotspring.model.parkingticket.ParkingTicket;
//import zely.parkinglotspring.model.payment.Cash;
//import zely.parkinglotspring.model.payment.CreditCard;
//import zely.parkinglotspring.model.payment.Payment;
//import zely.parkinglotspring.model.payment.PaymentStatus;
//import zely.parkinglotspring.repository.parkingticket.ParkingTicketRepository;
//import zely.parkinglotspring.repository.payment.PaymentRepository;
//
//import java.util.Optional;
//
//@Service
//public class PaymentServiceImpl {
//    private final PaymentRepository paymentRepository;
//
//
//    public PaymentServiceImpl(PaymentRepository paymentRepository, ParkingTicketRepository parkingTicketRepository) {
//        this.paymentRepository = paymentRepository;
//        this.parkingTicketRepository = parkingTicketRepository;
//    }
//
//
//    public boolean processPayment(PaymentDto paymentDto) {
//
//        Optional<ParkingTicket> ticketOptional = parkingTicketRepository.findById(ticketNumber);
//
//        if (ticketOptional.isEmpty()){
//            throw new RuntimeException("Ticket not found");
//        }
//
//        ParkingTicket ticket = ticketOptional.get();
//        Payment payment;
//
//        if (paymentMethod.equals("creditcard")){
//            payment = new CreditCard();
//        } else if (paymentMethod.equals("cash")){
//            payment = new Cash();
//        } else {
//            throw new RuntimeException("invalid payment method");
//        }
//
//        ticket.setPayment(payment);
//
//        if (payment instanceof CreditCard){
//            if (processCreditCard(amountToPay)){
//                payment.setPaymentStatus(PaymentStatus.COMPLETED);
//                return true;
//            } else {
//                payment.setPaymentStatus(PaymentStatus.FAILED);
//            }
//        } else if (payment instanceof Cash){
//            if (processCash(amountToPay)){
//                payment.setPaymentStatus(PaymentStatus.COMPLETED);
//                return true;
//            } else {
//                payment.setPaymentStatus(PaymentStatus.FAILED);
//            }
//        } else {
//            throw new RuntimeException("invalid payment method");
//        }
//
//        payment.setPaymentStatus(PaymentStatus.UNPAID);
//        paymentRepository.save(payment);
//
//        return false;
//    }
//
//    private boolean processCash(double amountToPay) {
////
////        if (amountToPay > receivedCash) {
////            throw new RuntimeException("insufficient money");
////        }
//
//        return true;
//
//    }
//
//    private boolean processCreditCard(double amountToPay) {
//
//        boolean validCreditCard = true;
//
//        if (!validCreditCard) {
//            throw new RuntimeException("invalid creditCard");
//        }
//
//        return true;
//
//    }
//
//}
//
