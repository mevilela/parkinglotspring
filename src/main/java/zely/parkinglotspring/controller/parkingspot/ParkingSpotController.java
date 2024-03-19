package zely.parkinglotspring.controller.parkingspot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.service.parkingspot.ParkingSpotService;

import java.util.List;
import java.util.Map;

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


    @PostMapping("/") //todo apenas o admin pode fazer essa operação
    public ResponseEntity<?> createNewSpot(@RequestBody ParkingSpot parkingSpot){

        ParkingSpot createdSpot = parkingSpotService.createNewParkingSpot(parkingSpot);

        return new ResponseEntity<>(createdSpot, HttpStatus.CREATED);

    }

    @PostMapping("/park")
    ResponseEntity<ParkingSpot> parkVehicle(@RequestBody ParkingSpot parkingSpot) {

        ParkingSpot parkedSpot = parkingSpotService.parkVehicle(parkingSpot);

        return new ResponseEntity<>(parkedSpot, HttpStatus.CREATED);
    }


}
