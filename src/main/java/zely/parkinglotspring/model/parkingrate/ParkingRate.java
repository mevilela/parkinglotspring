package zely.parkinglotspring.model.parkingrate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

import java.util.List;

@Entity
public class ParkingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    private double hours;

    private double parkingRate;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    @JsonIgnore
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingRate")
    @JsonIgnore
    private List<ParkingTicket> tickets;

    public ParkingRate() {
    }

    public ParkingRate( double parkingRate) {
        this.parkingRate = parkingRate;
    }

    public ParkingRate(double hours, double parkingRate, ParkingLot parkingLot) {
        this.hours = hours;
        this.parkingRate = parkingRate;
        this.parkingLot = parkingLot;
    }

    public Integer getId() {
        return id;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(double parkingRate) {
        this.parkingRate = parkingRate;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<ParkingTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<ParkingTicket> tickets) {
        this.tickets = tickets;
    }
}
