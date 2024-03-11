package zely.parkinglotspring.model.parkingticket;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import zely.parkinglotspring.model.vehicle.Vehicle;

import java.util.Date;

@Entity
public class ParkingTicket {

    @Id
    private Integer ticketNumber;

    private Date timestamp;

    private Date exit;

    private double rate;

    private double amount;

    private boolean status;

   // private Vehicle vehicle;

//    private Payment payment;
//
//    private Entrance entrance;
//
//    private Exit exit;


}
