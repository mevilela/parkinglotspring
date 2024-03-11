package zely.parkinglotspring.model.entrance;

import jakarta.persistence.*;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Entrance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "entrance")
    private List<ParkingTicket> tickets = new ArrayList<>();

    public Entrance() {
    }

    public Entrance(List<ParkingTicket> tickets) {
        this.tickets = tickets;
    }

    public List<ParkingTicket> getTickets() {
        return tickets;
    }

}
