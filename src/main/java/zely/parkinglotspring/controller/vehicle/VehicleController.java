package zely.parkinglotspring.controller.vehicle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
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


}
