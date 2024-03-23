package zely.parkinglotspring.manager;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.PaymentDto;
import zely.parkinglotspring.factory.PaymentServiceFactory;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.payment.Cash;
import zely.parkinglotspring.model.payment.CreditCard;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;
import zely.parkinglotspring.service.payment.PaymentService;

import java.util.Optional;

@Service
public class PaymentManager {
    ParkingTicketService parkingTicketService;

    public PaymentManager(ParkingTicketService parkingTicketService) {
        this.parkingTicketService = parkingTicketService;
    }

    public Payment processPayment(PaymentDto paymentDto) {

        Optional<ParkingTicket> ticketOptional = parkingTicketService.scanParkingTicket(paymentDto.getTicketNumber());

        if (ticketOptional.isEmpty()){
            throw new RuntimeException("Ticket not found");
        }

        ParkingTicket ticket = ticketOptional.get();

        Payment newPayment = switch (paymentDto.getPaymentMethod()){
            case "credicard" -> new CreditCard();
            case "cash" -> new Cash();
            default -> throw new RuntimeException("Unknown payment method informed");
        };

        newPayment.setParkingTicket(ticket);

        PaymentService paymentService = PaymentServiceFactory.getPaymentService(paymentDto.getPaymentMethod());

        newPayment = paymentService.processPayment(newPayment);

        ticket.setPayment(newPayment);

        parkingTicketService.updateParkingTicket(ticket);

        return newPayment;

    }

}
