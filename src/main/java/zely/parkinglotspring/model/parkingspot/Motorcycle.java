package zely.parkinglotspring.model.parkingspot;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("motorcycle")
public class Motorcycle extends ParkingSpot{
}
