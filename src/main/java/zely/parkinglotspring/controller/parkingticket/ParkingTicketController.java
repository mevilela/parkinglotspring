package zely.parkinglotspring.controller.parkingticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;

import java.util.List;

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
}
