package zely.parkinglotspring.model.exit;

import jakarta.persistence.*;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Exit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "exit")
    private List<ParkingTicket> tickets = new ArrayList<>();

    public Exit() {
    }

    public Exit(List<ParkingTicket> tickets) {
        this.tickets = tickets;
    }

    public List<ParkingTicket> getTickets() {
        return tickets;
    }

}
