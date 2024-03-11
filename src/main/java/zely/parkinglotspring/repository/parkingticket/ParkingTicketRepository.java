package zely.parkinglotspring.repository.parkingticket;

import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

public interface ParkingTicketRepository extends CrudRepository<ParkingTicket, Integer> {
}
