package zely.parkinglotspring.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;
import zely.parkinglotspring.service.payment.PaymentService;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final ParkingTicketService parkingTicketService;

    public PaymentController(PaymentService paymentService, ParkingTicketService parkingTicketService) {
        this.paymentService = paymentService;
        this.parkingTicketService = parkingTicketService;
    }

    @PostMapping("/") //todo parkingAgent and customer allowed
    public ResponseEntity<String> payTicket(@RequestParam Integer ticketNumber, @RequestParam String paymentMethod){
      Optional<ParkingTicket> ticket = parkingTicketService.scanParkingTicket(ticketNumber);
        if(ticket.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        double amountToPay = ticket.get().getAmount();
        boolean paymentProcessed = paymentService.processPayment(ticketNumber, amountToPay, paymentMethod);

        if(paymentProcessed){

            return ResponseEntity.ok("successfully paid");

        } else {
            return ResponseEntity.internalServerError().body("Payment failed");
        }
    }
}
