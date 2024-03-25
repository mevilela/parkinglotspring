package zely.parkinglotspring.model.displayboard;

import jakarta.persistence.*;
import zely.parkinglotspring.dto.DisplayBoardItemDto;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class DisplayBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private List<DisplayBoardItemDto> displayBoardItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    public DisplayBoard() {
    }

    public Integer getId() {
        return id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}