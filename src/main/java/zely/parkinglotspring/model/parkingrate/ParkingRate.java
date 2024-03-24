package zely.parkinglotspring.model.parkingrate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

//    private double hours;

    private double parkingRate;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    @JsonIgnore
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingRate")
    @JsonIgnore
    private List<ParkingTicket> tickets;

    private String vehicleType;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingRate() {
    }

    public ParkingRate(double parkingRate, String vehicleType) {
        this.parkingRate = parkingRate;
        this.vehicleType = vehicleType;
    }

    public ParkingRate(double parkingRate) {
        this.parkingRate = parkingRate;
    }

    public ParkingRate(double parkingRate, ParkingLot parkingLot, String vehicleType) {
        this.parkingRate = parkingRate;
        this.parkingLot = parkingLot;
        this.vehicleType = vehicleType;
    }


    public Integer getId() {
        return id;
    }

//    public double getHours() {
//        return hours;
//    }
//
//    public void setHours(double hours) {
//        this.hours = hours;
//    }

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
