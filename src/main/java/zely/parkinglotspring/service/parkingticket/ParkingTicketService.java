package zely.parkinglotspring.service.parkingticket;

import org.springframework.stereotype.Service;
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
    private final EntranceRepository entranceRepository;
    private final VehicleRepository vehicleRepository;
    public ParkingTicketService(ParkingTicketRepository parkingTicketRepository, EntranceRepository entranceRepository, VehicleRepository vehicleRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.entranceRepository = entranceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<ParkingTicket> getAllParkingTickets(){

        return (List<ParkingTicket>) parkingTicketRepository.findAll();

    }

    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket) {

        parkingTicket.setTimestamp(LocalDateTime.now());

        Entrance entrance = entranceRepository.save(new Entrance());

        parkingTicket.setEntrance(entrance);

        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(parkingTicket.getVehicle().getId());
        if (vehicleOptional.isEmpty()){
            throw  new RuntimeException("Vehicle not found");
        }

        parkingTicket.setVehicle(vehicleOptional.get());

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
