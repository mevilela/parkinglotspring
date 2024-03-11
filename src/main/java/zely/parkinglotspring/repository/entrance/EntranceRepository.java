package zely.parkinglotspring.repository.entrance;

import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.entrance.Entrance;

public interface EntranceRepository extends CrudRepository<Entrance, Integer> {
}
