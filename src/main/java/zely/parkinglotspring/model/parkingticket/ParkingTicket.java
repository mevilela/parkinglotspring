package zely.parkinglotspring.model.parkingticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.model.exit.Exit;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.model.vehicle.Vehicle;

import java.time.LocalDateTime;

@Entity
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer ticketNumber;

    private LocalDateTime timestamp;

    private LocalDateTime exit_time;

    @ManyToOne
    @JoinColumn(name = "parking_rate_id")
    private ParkingRate parkingRate;

    private double amount;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;

    @OneToOne(mappedBy = "parkingTicket", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "entrance_id")
    private Entrance entrance;

    @ManyToOne
    @JoinColumn(name = "exit_id")
    private Exit exit;


    public ParkingTicket() {
    }

    public ParkingTicket(LocalDateTime timestamp, LocalDateTime exit_time, ParkingRate parkingRate, double amount, Vehicle vehicle, Payment payment, Entrance entrance, Exit exit) {
        this.ticketNumber = ticketNumber;
        this.timestamp = timestamp;
        this.exit_time = exit_time;
        this.parkingRate = parkingRate;
        this.amount = amount;
        this.vehicle = vehicle;
        this.payment = payment;
        this.entrance = entrance;
        this.exit = exit;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getExit_time() {
        return exit_time;
    }

    public void setExit_time(LocalDateTime exit_time) {
        this.exit_time = exit_time;
    }

    public ParkingRate getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(ParkingRate parkingRate) {
        this.parkingRate = parkingRate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public void setEntrance(Entrance entrance) {
        this.entrance = entrance;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }
}