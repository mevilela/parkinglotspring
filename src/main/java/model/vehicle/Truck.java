package model.vehicle;

import jakarta.persistence.Entity;
import model.ParkingTicket;
import model.vehicle.Vehicle;

@Entity
public class Truck extends Vehicle {
    @Override
    public void assignTicket(ParkingTicket ticket) {

    }
}
