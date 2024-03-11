package zely.parkinglotspring.model.vehicle;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

@Entity
@DiscriminatorValue("truck")
public class Truck extends Vehicle {
    @Override
    public void assignTicket(ParkingTicket ticket) {

    }
}
