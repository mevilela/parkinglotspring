package zely.parkinglotspring.model.parkingrate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.parkinglot.ParkingLot;

@Entity
public class ParkingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    private double hours;

    private double rate;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

   // public void calculate(){}


    public ParkingRate() {
    }

    public ParkingRate(double hours, double rate, ParkingLot parkingLot) {
        this.hours = hours;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
