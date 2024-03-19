package zely.parkinglotspring.manager;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.CreateParkingTicketDTO;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.service.EntranceService;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;
import zely.parkinglotspring.service.vehicle.VehicleService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingTicketManager {

    private final EntranceService entranceService;
    private final VehicleService vehicleService;
    private final ParkingTicketService parkingTicketService;

    public ParkingTicketManager(EntranceService entranceService, VehicleService vehicleService, ParkingTicketService parkingTicketService) {
        this.entranceService = entranceService;
        this.vehicleService = vehicleService;
        this.parkingTicketService = parkingTicketService;
    }

    //Rollback database transactions in case of error
    @Transactional
    public ParkingTicket createParkingTicket(CreateParkingTicketDTO createParkingTicketDTO) {
        // chamar os outros services
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setTimestamp(LocalDateTime.now());

        Vehicle vehicle = vehicleService.findByLicenseNo(createParkingTicketDTO.getVehicleLicenseNo());

        if (vehicle.getLicenseNo().isEmpty()){
            vehicleService.createNewVehicle(vehicle);
        }


//        Optional<Vehicle> vehicleOptional = vehicleService.findById(createParkingTicketDTO.getVehicleId());
//        if (vehicleOptional.isEmpty()){
//            throw  new RuntimeException("Vehicle not found");
//        }


        Entrance entrance = entranceService.createEntrance(new Entrance());
        parkingTicket.setEntrance(entrance);

        parkingTicket.setVehicle(vehicle);

        return parkingTicketService.createParkingTicket(parkingTicket);
    }




}
