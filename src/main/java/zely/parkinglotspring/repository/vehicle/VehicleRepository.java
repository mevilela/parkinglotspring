package zely.parkinglotspring.repository.vehicle;

import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.vehicle.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
}
