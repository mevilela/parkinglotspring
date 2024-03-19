package zely.parkinglotspring.service.parkingspot;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingspot.*;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.repository.parkinglot.ParkingLotRepository;
import zely.parkinglotspring.repository.parkingspot.ParkingSpotRepository;
import zely.parkinglotspring.repository.vehicle.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final VehicleRepository vehicleRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, ParkingLotRepository parkingLotRepository, VehicleRepository vehicleRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<ParkingSpot> getAllParkingSpots() {
       return (List<ParkingSpot>) parkingSpotRepository.findAll();
    }

    public ParkingSpot createNewParkingSpot(ParkingSpot parkingSpot) {

        ParkingLot defaultParkingLot = parkingLotRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("ParkingLot id not found"));

        parkingSpot.setParkingLot(defaultParkingLot);

        return parkingSpotRepository.save(parkingSpot);

    }

    public ParkingSpot parkVehicle(ParkingSpot parkingSpot) {

        // maybe also check the parking spot type
        // with the Vehicle type if they are allowed
        Optional<Vehicle> optionalVehicleToPark = vehicleRepository.findById(parkingSpot.getVehicle().getId());

        if (optionalVehicleToPark.isEmpty()){
            throw new RuntimeException("Vehicle not found");
        }

        Optional<ParkingSpot> optionalSpot = parkingSpotRepository.findById(parkingSpot.getId());

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
        vehicleRepository.save(vehicle);

        return parkingSpotRepository.save(parkingSpot);
    }
}
