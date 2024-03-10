package zely.parkinglotspring.model.parkingspot;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("handicapped")
public class Handicapped extends ParkingSpot{
}
