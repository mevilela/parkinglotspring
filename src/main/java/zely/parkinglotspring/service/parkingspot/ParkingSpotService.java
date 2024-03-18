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

    public ParkingSpot parkVehicle(Integer vehicleId, Integer spotId) {

        Optional<Vehicle> optionalVehicleToPark = vehicleRepository.findById(vehicleId);

        if (optionalVehicleToPark.isEmpty()){
            throw new RuntimeException("Vehicle not found");
        }

        Optional<ParkingSpot> optionalSpot = parkingSpotRepository.findById(spotId);

        if (optionalSpot.isEmpty()){
            throw new RuntimeException("Spot not found");
        }

        optionalSpot.get().setFree(false);
        vehicleRepository.save(optionalVehicleToPark.get());
        optionalSpot.get().setVehicle(optionalVehicleToPark.get());

        return parkingSpotRepository.save(optionalSpot.get());
    }
}
