package zely.parkinglotspring.model.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import zely.parkinglotspring.model.parkingspot.*;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "vehicle_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = Moto.class, name = "moto"),
        @JsonSubTypes.Type(value = Truck.class, name = "truck"),
        @JsonSubTypes.Type(value = Van.class, name = "van")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    private String licenseNo;

    @OneToOne
    @JoinColumn(name = "parking_spot_id")
    @JsonIgnore
    private ParkingSpot parkingSpot;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<ParkingTicket> parkingTickets;


    public abstract void assignTicket(ParkingTicket ticket);

    public Vehicle() {
    }

    public Vehicle(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Integer getId() {
        return id;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public List<ParkingTicket> getParkingTickets() {
        return parkingTickets;
    }

    public void setParkingTickets(List<ParkingTicket> parkingTickets) {
        this.parkingTickets = parkingTickets;
    }
}