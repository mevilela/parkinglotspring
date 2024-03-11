package zely.parkinglotspring.service.vehicle;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.repository.vehicle.VehicleRepository;

import java.util.List;

@Service
public class VehicleService {

    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }
}
