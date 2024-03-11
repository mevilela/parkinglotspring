package zely.parkinglotspring.service.parkingticket;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.account.Account;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.repository.parkingticket.ParkingTicketRepository;

import java.util.List;

@Service
public class ParkingTicketService {

    private final ParkingTicketRepository parkingTicketRepository;
    public ParkingTicketService(ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
    }

    public List<ParkingTicket> getAllParkingTickets(){

        return (List<ParkingTicket>) parkingTicketRepository.findAll();

    }





}
