package zely.parkinglotspring.repository.displayboard;

import org.springframework.data.repository.CrudRepository;
import zely.parkinglotspring.model.displayboard.DisplayBoard;

public interface DisplayBoardRepository extends CrudRepository<DisplayBoard, Integer> {
}
