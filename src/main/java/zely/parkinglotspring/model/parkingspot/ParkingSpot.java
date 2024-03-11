package zely.parkinglotspring.model.parkingspot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Customer;
import zely.parkinglotspring.model.account.ParkingAgent;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.vehicle.Vehicle;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "spot_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Handicapped.class, name = "handicapped"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "motorcycle"),
        @JsonSubTypes.Type(value = Large.class, name = "large"),
        @JsonSubTypes.Type(value = Compact.class, name = "compact")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "spot_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ParkingSpot {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    private boolean free = true;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    @JsonIgnore
    private ParkingLot parkingLot;

    @OneToOne(mappedBy = "parkingSpot")
    private Vehicle vehicle;

    public ParkingSpot(){

    }
    public ParkingSpot(Integer id, boolean free) {
        this.id = id;
        this.free = free;
    }

    public Integer getId() {
        return id;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
