package repository.account;

import model.account.ParkingAgent;
import org.springframework.data.repository.CrudRepository;

public interface ParkingAgentRepository extends CrudRepository<ParkingAgent, Integer> {
}
