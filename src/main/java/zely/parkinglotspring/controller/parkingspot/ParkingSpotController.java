package zely.parkinglotspring.controller.parkingspot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.service.parkingspot.ParkingSpotService;

import java.util.List;

@RestController
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }


    @GetMapping
    public ResponseEntity<List<ParkingSpot>> getAllParkingSpots(){

       return ResponseEntity.ok(parkingSpotService.getAllParkingSpots());
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewSpot(@RequestBody ParkingSpot parkingSpot){

        ParkingSpot createdSpot = parkingSpotService.createNewParkingSpot(parkingSpot);

        return new ResponseEntity<>(createdSpot, HttpStatus.CREATED);

    }

}
