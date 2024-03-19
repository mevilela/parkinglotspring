package zely.parkinglotspring.manager;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.service.parkingspot.ParkingSpotService;
import zely.parkinglotspring.service.vehicle.VehicleService;

import java.util.Optional;

@Service
public class ParkingSpotManager {

    private final VehicleService vehicleService;
    private final ParkingSpotService parkingSpotService;

    public ParkingSpotManager(VehicleService vehicleService, ParkingSpotService parkingSpotService) {
        this.vehicleService = vehicleService;
        this.parkingSpotService = parkingSpotService;
    }

    public ParkingSpot parkVehicle(ParkingSpot parkingSpot) {

        // maybe also check the parking spot type
        // with the Vehicle type if they are allowed

        Optional<Vehicle> optionalVehicleToPark = vehicleService.findById(parkingSpot.getVehicle().getId());

        if (optionalVehicleToPark.isEmpty()){
            throw new RuntimeException("Vehicle not found");
        }

        Optional<ParkingSpot> optionalSpot = parkingSpotService.findParkingSpotById(parkingSpot.getId());

        if (optionalSpot.isEmpty()){
            throw new RuntimeException("Spot not found");
        }

        parkingSpot = optionalSpot.get();

        if(!parkingSpot.isFree()) {
            throw new RuntimeException("Parking Spot is not free!");
        }

        Vehicle vehicle = optionalVehicleToPark.get();

        parkingSpot.setFree(false);
        parkingSpot.setVehicle(vehicle);

        vehicle.setParkingSpot(parkingSpot);

        return parkingSpotService.createNewParkingSpot(parkingSpot);
    }
}
