package zely.parkinglotspring.model.parkingspot;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("compact")
public class Compact extends ParkingSpot{
}
