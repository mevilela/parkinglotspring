package zely.parkinglotspring.repository.parkingrate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

import java.util.List;

public interface ParkingRateRepository extends CrudRepository<ParkingRate, Integer> {

    ParkingRate findParkingRateByVehicleType(String vehicleType);

}


