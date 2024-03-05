package model.vehicle;

import jakarta.persistence.Entity;
import model.ParkingTicket;

@Entity
public class Motorcycle extends Vehicle {
    @Override
    public void assignTicket(ParkingTicket ticket) {

    }
}
