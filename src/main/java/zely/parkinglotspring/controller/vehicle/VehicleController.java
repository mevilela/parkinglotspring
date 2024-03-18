package zely.parkinglotspring.controller.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.service.vehicle.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());

    }

    @PostMapping("/")
    public ResponseEntity<Vehicle> createNewVehicle(@RequestBody Vehicle vehicle){

        Vehicle newVehicle = vehicleService.createNewVehicle(vehicle);

        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }


}
