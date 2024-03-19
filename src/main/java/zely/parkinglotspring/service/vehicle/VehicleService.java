package zely.parkinglotspring.service.vehicle;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.repository.vehicle.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    public Vehicle createNewVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);

    }

    public Vehicle findByLicenseNo(String vehicleLicenseNo){
        return vehicleRepository.findVehicleByLicenseNo(vehicleLicenseNo);
    }

    public Optional<Vehicle> findById(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }
}
