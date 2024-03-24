package zely.parkinglotspring.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

import java.time.LocalDateTime;

@JsonSubTypes({
        @JsonSubTypes.Type(value = Cash.class, name = "cash"),
        @JsonSubTypes.Type(value = CreditCard.class, name = "credit_card"),
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentTimeStamp;

    @OneToOne
    @JoinColumn(name = "parking_ticket_id")
    @JsonIgnore
    private ParkingTicket parkingTicket;


    public Payment() {
    }

    public Payment(double amount, PaymentStatus paymentStatus, LocalDateTime paymentTimeStamp, ParkingTicket parkingTicket) {
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentTimeStamp = paymentTimeStamp;
        this.parkingTicket = parkingTicket;
    }

    public Integer getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTimeStamp() {
        return paymentTimeStamp;
    }

    public void setPaymentTimeStamp(LocalDateTime paymentTimeStamp) {
        this.paymentTimeStamp = paymentTimeStamp;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }
}
