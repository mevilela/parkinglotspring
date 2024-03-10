package zely.parkinglotspring.model.parkinglot;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.address.Address;
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

    public ParkingLot() {
    }

    public ParkingLot(Integer id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
