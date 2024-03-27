package zely.parkinglotspring.controller.parkingspot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.dto.ParkVehicleDto;
import zely.parkinglotspring.manager.ParkingSpotManager;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.service.parkingspot.ParkingSpotService;

import java.util.List;

@RestController
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;
    private final ParkingSpotManager parkingSpotManager;

    public ParkingSpotController(ParkingSpotService parkingSpotService, ParkingSpotManager parkingSpotManager) {
        this.parkingSpotService = parkingSpotService;
        this.parkingSpotManager = parkingSpotManager;
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
    ResponseEntity<ParkingSpot> parkVehicle(@RequestBody ParkVehicleDto parkVehicleDto) {

        ParkingSpot parkedSpot = parkingSpotManager.parkVehicle(parkVehicleDto);

        return new ResponseEntity<>(parkedSpot, HttpStatus.OK);
    }


}
