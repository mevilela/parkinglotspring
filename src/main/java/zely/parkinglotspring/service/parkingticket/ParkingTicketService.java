package zely.parkinglotspring.service.parkingticket;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.CreateParkingTicketDTO;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.repository.entrance.EntranceRepository;
import zely.parkinglotspring.repository.parkingticket.ParkingTicketRepository;
import zely.parkinglotspring.repository.vehicle.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingTicketService {

    private final ParkingTicketRepository parkingTicketRepository;
    public ParkingTicketService(ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
    }

    public List<ParkingTicket> getAllParkingTickets(){

        return (List<ParkingTicket>) parkingTicketRepository.findAll();

    }

    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket) {
        return parkingTicketRepository.save(parkingTicket);
    }


    public Optional<ParkingTicket> scanParkingTicket(Integer ticketNumber) {

        if (ticketNumber != null){

            return parkingTicketRepository.findById(ticketNumber);

        } else {
            throw new RuntimeException("Invalid ticket");
        }
    }


}
