package zely.parkinglotspring.manager;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.controller.parkingspot.ParkingSpotController;
import zely.parkinglotspring.dto.ParkVehicleDto;
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

    @Transactional
    public ParkingSpot parkVehicle(ParkVehicleDto parkVehicleDto) {

        // maybe also check the parking spot type
        // with the Vehicle type if they are allowed

        Optional<Vehicle> optionalVehicleToPark = vehicleService.findById(parkVehicleDto.getVehicleId());

        if (optionalVehicleToPark.isEmpty()){
            throw new RuntimeException("Vehicle not found");
        }

        Optional<ParkingSpot> optionalSpot = parkingSpotService.findParkingSpotById(parkVehicleDto.getSpotId());

        if (optionalSpot.isEmpty()){
            throw new RuntimeException("Spot not found");
        }

        ParkingSpot parkingSpot = optionalSpot.get();


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
