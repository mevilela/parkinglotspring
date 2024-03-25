package zely.parkinglotspring.repository.parkingspot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;

import java.util.List;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {
    @Query("SELECT ps FROM ParkingSpot ps WHERE TYPE(ps) = :spotType ")
    List<ParkingSpot> getParkingSpotsBySpotType(@Param("spotType") Class<? extends ParkingSpot> spotType);

    @Query("SELECT COUNT(ps) FROM ParkingSpot ps WHERE TYPE(ps) = :spotType AND ps.free = false")
    long countOccupiedSpotsByType(@Param("spotType") Class<? extends ParkingSpot> spotType);

    @Query("SELECT COUNT(ps) FROM ParkingSpot ps WHERE TYPE(ps) = :spotType AND ps.free = true")
    long countFreeSpotsByType(@Param("spotType") Class<? extends ParkingSpot> spotType);

    @Query("SELECT COUNT(ps) FROM ParkingSpot ps WHERE TYPE(ps) = :spotType")
    long countTotalSpotsByType(@Param("spotType") Class<? extends  ParkingSpot> spotType);
}
