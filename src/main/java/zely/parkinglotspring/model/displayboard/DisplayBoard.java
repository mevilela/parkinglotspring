package zely.parkinglotspring.model.displayboard;

import jakarta.persistence.*;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class DisplayBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient //not persistent - managed programmatically
    private Map<String, List<ParkingSpot>> parkingSpotsByType = new HashMap<>();

    @OneToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    public DisplayBoard() {
    }

    public DisplayBoard(Map<String, List<ParkingSpot>> parkingSpotsByType) {
        this.parkingSpotsByType = parkingSpotsByType;
    }

    public Integer getId() {
        return id;
    }


    public Map<String, List<ParkingSpot>> getParkingSpotsByType() {
        return parkingSpotsByType;
    }

    public void setParkingSpotsByType(Map<String, List<ParkingSpot>> parkingSpotsByType) {
        this.parkingSpotsByType = parkingSpotsByType;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}