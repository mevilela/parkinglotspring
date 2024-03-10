package zely.parkinglotspring.model.parkingspot;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("large")
public class Large extends ParkingSpot{


}
