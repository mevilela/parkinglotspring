package zely.parkinglotspring.repository.parkingspot;

import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {
}
