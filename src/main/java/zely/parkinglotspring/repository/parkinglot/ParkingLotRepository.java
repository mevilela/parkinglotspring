package zely.parkinglotspring.repository.parkinglot;

import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.parkinglot.ParkingLot;

public interface ParkingLotRepository extends CrudRepository<ParkingLot, Integer> {
}
