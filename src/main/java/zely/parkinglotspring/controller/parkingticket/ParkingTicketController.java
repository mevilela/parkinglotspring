package zely.parkinglotspring.controller.parkingticket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.dto.CreateParkingTicketDTO;
import zely.parkinglotspring.dto.FinalizeParkingDto;
import zely.parkinglotspring.manager.ParkingTicketManager;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class ParkingTicketController {

    private final ParkingTicketService parkingTicketService;

    private final ParkingTicketManager parkingTicketManager;

    public ParkingTicketController(ParkingTicketService parkingTicketService, ParkingTicketManager parkingTicketManager) {
        this.parkingTicketService = parkingTicketService;
        this.parkingTicketManager = parkingTicketManager;
    }

    @GetMapping
    public ResponseEntity<List<ParkingTicket>> getAllParkingTickets(){

        return ResponseEntity.ok(parkingTicketService.getAllParkingTickets());

    }

    @GetMapping("{ticketNumber}") //todo apenas customer e parking agent
    public ResponseEntity<ParkingTicket> scanParkingTicket(@RequestParam @PathVariable Integer ticketNumber){
        return ResponseEntity.ok(parkingTicketService.scanParkingTicket(ticketNumber));
    }

    @PostMapping("/")
    public ResponseEntity<ParkingTicket> createParkingTicket(@RequestBody CreateParkingTicketDTO createParkingTicketDTO){
        ParkingTicket newTicket = parkingTicketManager.createParkingTicket(createParkingTicketDTO);

        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    @PutMapping("/finalize-parking")
    public ResponseEntity<ParkingTicket> finalizeParking(@RequestBody FinalizeParkingDto finalizeParkingDto){
        ParkingTicket finalizedParking = parkingTicketManager.finalizeParking(finalizeParkingDto);

        return new ResponseEntity<>(finalizedParking, HttpStatus.CREATED);
    }

    @PutMapping("/exit-parking")
    public ResponseEntity<ParkingTicket> exitParking(@RequestBody FinalizeParkingDto finalizeParkingDto){
        ParkingTicket exitParking = parkingTicketManager.exitParking(finalizeParkingDto);

        return new ResponseEntity<>(exitParking, HttpStatus.CREATED);
    }


}
