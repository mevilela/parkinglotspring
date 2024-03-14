package zely.parkinglotspring.model.parkinglot;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.address.Address;
import zely.parkinglotspring.model.displayboard.DisplayBoard;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String name;

    @Embedded
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Address address;

    @OneToMany
    @JoinColumn(name = "parking_lot_id")
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<ParkingRate> parkingRates;

    @OneToOne(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private DisplayBoard displayBoard;

    public ParkingLot() {
    }

    public ParkingLot(Integer id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public List<ParkingRate> getParkingRates() {
        return parkingRates;
    }

    public void setParkingRates(List<ParkingRate> parkingRates) {
        this.parkingRates = parkingRates;
    }

    public DisplayBoard getDisplayBoard() {
        return displayBoard;
    }

    public void setDisplayBoard(DisplayBoard displayBoard) {
        this.displayBoard = displayBoard;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
