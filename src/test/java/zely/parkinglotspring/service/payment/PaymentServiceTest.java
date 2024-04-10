package zely.parkinglotspring.service.payment;

import jakarta.servlet.http.Part;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.payment.Cash;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.model.payment.PaymentStatus;
import zely.parkinglotspring.repository.payment.PaymentRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentService paymentService;


    @Test
    void when_processPaymentIsCorrect_willReturn_paymentStatusCompleted() {

        //given
        ParkingTicket ticket = new ParkingTicket();
        ticket.setAmount(2);
        Cash cash = new Cash();
        cash.setAmount(2);
        cash.setParkingTicket(ticket);

        // When
        paymentService.processPayment(cash);

        // Then
        assertEquals(PaymentStatus.COMPLETED, cash.getPaymentStatus());

   }

    @Test
    void when_processPaymentIsIncorrect_willReturn_paymentStatusFailed() {

        //given
        ParkingTicket ticket = new ParkingTicket();
        ticket.setAmount(3);
        Cash cash = new Cash();
        cash.setAmount(2);
        cash.setParkingTicket(ticket);

        // When
        paymentService.processPayment(cash);

        // Then
        assertEquals(PaymentStatus.FAILED, cash.getPaymentStatus());

    }

    @Test
    void when_processPaymentIsNull_willReturn_throwIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.processPayment(null);
        });

    }

}