package zely.parkinglotspring.controller.parkingticket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class ParkingTicketController {

    private final ParkingTicketService parkingTicketService;
    public ParkingTicketController(ParkingTicketService parkingTicketService) {
        this.parkingTicketService = parkingTicketService;
    }

    @GetMapping
    public ResponseEntity<List<ParkingTicket>> getAllParkingTickets(){

        return ResponseEntity.ok(parkingTicketService.getAllParkingTickets());

    }

    @GetMapping("{ticketNumber}") //todo apenas customer e parking agent
    public ResponseEntity<Optional<ParkingTicket>> scanParkingTicket(@RequestParam @PathVariable Integer ticketNumber){
        return ResponseEntity.ok(parkingTicketService.scanParkingTicket(ticketNumber));
    }

    @PostMapping("/")
    public ResponseEntity<ParkingTicket> createParkingTicket(@RequestBody ParkingTicket parkingTicket){
        ParkingTicket newTicket = parkingTicketService.createParkingTicket(parkingTicket);

        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }


}
