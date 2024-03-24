package zely.parkinglotspring.manager;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.CreateParkingTicketDTO;
import zely.parkinglotspring.dto.FinalizeParkingDto;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.model.exit.Exit;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.payment.PaymentStatus;
import zely.parkinglotspring.model.vehicle.*;
import zely.parkinglotspring.service.entrance.EntranceService;
import zely.parkinglotspring.service.exitService.ExitService;
import zely.parkinglotspring.service.parkingrate.ParkingRateService;
import zely.parkinglotspring.service.parkingspot.ParkingSpotService;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;
import zely.parkinglotspring.service.vehicle.VehicleService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class ParkingTicketManager {

    private final EntranceService entranceService;
    private final VehicleService vehicleService;
    private final ParkingTicketService parkingTicketService;
    private final ParkingRateService parkingRateService;
    private final ParkingSpotService parkingSpotService;

    private final ExitService exitService;


    public ParkingTicketManager(EntranceService entranceService, VehicleService vehicleService, ParkingTicketService parkingTicketService, ParkingRateService parkingRateService, ParkingSpotService parkingSpotService, ExitService exitService) {
        this.entranceService = entranceService;
        this.vehicleService = vehicleService;
        this.parkingTicketService = parkingTicketService;
        this.parkingRateService = parkingRateService;
        this.parkingSpotService = parkingSpotService;
        this.exitService = exitService;
    }

    //Rollback database transactions in case of error
    @Transactional
    public ParkingTicket createParkingTicket(CreateParkingTicketDTO createParkingTicketDTO) {
        // chamar os outros services
        ParkingTicket parkingTicket = new ParkingTicket();

        parkingTicket.setTimestamp(LocalDateTime.now());

        Vehicle vehicle = vehicleService.findByLicenseNo(createParkingTicketDTO.getVehicleLicenseNo());


        //if no vehicle yet, create one by type
        if (vehicle == null){

            if(createParkingTicketDTO.getVehicleType() == null ) {
                throw new RuntimeException("Vehicle type is required and Vehicle is not in the database yet.");
            }

            Vehicle newVehicle = switch (createParkingTicketDTO.getVehicleType()) {
                case "car" -> new Car();
                case "moto" -> new Moto();
                case "truck" -> new Truck();
                case "van" -> new Van();
                default -> throw new RuntimeException("Unknown vehicle type informed");
            };

            newVehicle.setLicenseNo(createParkingTicketDTO.getVehicleLicenseNo());
            vehicle = vehicleService.createNewVehicle(newVehicle);
        }

        Entrance entrance = entranceService.createEntrance(new Entrance());
        parkingTicket.setEntrance(entrance);

        parkingTicket.setVehicle(vehicle);

        String vehicleType = createParkingTicketDTO.getVehicleType();

        ParkingRate rate = parkingRateService.getParkingRateByVehicleType(vehicleType);

        parkingTicket.setParkingRate(rate);

        return parkingTicketService.createParkingTicket(parkingTicket);
    }

    public ParkingTicket finalizeParking(FinalizeParkingDto finalizeParkingDto) {

        Optional<ParkingTicket> ticketOptional = parkingTicketService.scanParkingTicket(finalizeParkingDto.getTicketNumber());

        if (ticketOptional.isEmpty()) {
            throw new RuntimeException("Invalid parking ticket");
        }

        ParkingTicket ticket = ticketOptional.get();

        ticket.setExitTime(LocalDateTime.now());

        double parkingHours = ticket.getTimestamp().until(ticket.getExitTime(), ChronoUnit.HOURS);

        double amountToPay = ticket.getParkingRate().getParkingRate() * parkingHours;

        ticket.setAmount(amountToPay);

        return parkingTicketService.updateTicket(ticket);

    }

    public ParkingTicket exitParking(FinalizeParkingDto finalizeParkingDto) {

        Optional<ParkingTicket> ticketOptional = parkingTicketService.scanParkingTicket(finalizeParkingDto.getTicketNumber());

        if (ticketOptional.isEmpty()) {
            throw new RuntimeException("Invalid parking ticket");
        }

        ParkingTicket ticket = ticketOptional.get();

        if(!ticket.getPayment().getPaymentStatus().equals(PaymentStatus.COMPLETED)){
            throw new RuntimeException("Please check your ticket payment");
        }

        Exit exit = new Exit();

        exitService.createExit(exit);

        ticket.setExit(exit);

        parkingTicketService.updateTicket(ticket);

        if (ticket.getExit() == exit){

            ParkingSpot parkingSpotToBeFree = ticket.getVehicle().getParkingSpot();
            parkingSpotToBeFree.setFree(true);
            parkingSpotService.updateParkingSpot(parkingSpotToBeFree);
        }

        return parkingTicketService.updateTicket(ticket);
    }
}

