package zely.parkinglotspring.model.vehicle;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

@Entity
@DiscriminatorValue("car")

public class Car extends Vehicle{
    @Override
    public void assignTicket(ParkingTicket ticket) {

    }
}
