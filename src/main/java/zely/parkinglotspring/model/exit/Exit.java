package zely.parkinglotspring.model.exit;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<ParkingTicket> tickets = new ArrayList<>();

    public Exit() {
    }
    @JsonIgnore
    public Exit(List<ParkingTicket> tickets) {
        this.tickets = tickets;
    }
    @JsonIgnore
    public List<ParkingTicket> getTickets() {
        return tickets;
    }

}
