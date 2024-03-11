package zely.parkinglotspring.repository.parkingspot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {
    @Query("SELECT ps FROM ParkingSpot ps WHERE TYPE(ps) = :spotType")
    ParkingSpot getParkingSpotBySpotType(@Param("spotType") String spotType);
}
