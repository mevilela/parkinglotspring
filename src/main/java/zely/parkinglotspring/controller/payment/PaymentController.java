package zely.parkinglotspring.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.parkinglotspring.dto.PaymentDto;
import zely.parkinglotspring.manager.PaymentManager;
import zely.parkinglotspring.model.payment.Payment;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentManager paymentManager;

    public PaymentController(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    @PostMapping("/") //todo parkingAgent and customer allowed
    public ResponseEntity<Payment> payTicket(@RequestBody PaymentDto paymentDto){

        return ResponseEntity.ok(paymentManager.processPayment(paymentDto));

    }
}
