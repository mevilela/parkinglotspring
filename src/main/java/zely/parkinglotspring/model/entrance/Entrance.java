package zely.parkinglotspring.model.entrance;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<ParkingTicket> tickets = new ArrayList<>();

    public Entrance() {
    }

    @JsonIgnore
    public Entrance(List<ParkingTicket> tickets) {
        this.tickets = tickets;
    }
    @JsonIgnore
    public List<ParkingTicket> getTickets() {
        return tickets;
    }

}
