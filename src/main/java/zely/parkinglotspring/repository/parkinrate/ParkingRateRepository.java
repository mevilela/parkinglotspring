package zely.parkinglotspring.repository.parkinrate;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.parkingrate.ParkingRate;

public interface ParkingRateRepository extends CrudRepository<ParkingRate, Integer> {
}
