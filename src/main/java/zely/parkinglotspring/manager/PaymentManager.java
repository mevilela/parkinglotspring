package zely.parkinglotspring.manager;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.PaymentDto;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.payment.Cash;
import zely.parkinglotspring.model.payment.CreditCard;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.model.payment.PaymentStatus;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;
import zely.parkinglotspring.service.payment.PaymentService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentManager {

    PaymentService paymentService;
    ParkingTicketService parkingTicketService;

    public PaymentManager(ParkingTicketService parkingTicketService, PaymentService paymentService) {
        this.parkingTicketService = parkingTicketService;
        this.paymentService = paymentService;
    }

    public Payment processPayment(PaymentDto paymentDto) {

       ParkingTicket ticket = parkingTicketService.scanParkingTicket(paymentDto.getTicketNumber());

        if(ticket.getPayment() != null && ticket.getPayment().getPaymentStatus().equals(PaymentStatus.COMPLETED))  {
            throw new RuntimeException("Ticket already paid.");
        }

        // if payment exists, just load it from the ticket
        // otherwise create new payment
        Payment payment = ticket.getPayment() != null ? ticket.getPayment() :
                switch (paymentDto.getPaymentMethod()){
                    case "creditcard" -> new CreditCard();
                    case "cash" -> new Cash();
                    default -> throw new RuntimeException("Unknown payment method informed");
                };

        payment.setAmount(paymentDto.getAmountToPay());
        payment.setPaymentTimeStamp(LocalDateTime.now());
        payment.setParkingTicket(ticket);

        payment = paymentService.processPayment(payment);

        ticket.setPayment(payment);

        parkingTicketService.updateTicket(ticket);

        return payment;

    }

}
