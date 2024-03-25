package zely.parkinglotspring.service.displayboard;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.displayboard.DisplayBoard;
import zely.parkinglotspring.repository.displayboard.DisplayBoardRepository;

import java.util.Optional;

@Service
public class DisplayBoardService {

    DisplayBoardRepository displayBoardRepository;

    public Optional<DisplayBoard> getDisplayBoardById(Integer id){

        return displayBoardRepository.findById(id);
    }

    public DisplayBoard createDisplayBoard(DisplayBoard displayBoard){
        return displayBoardRepository.save(displayBoard);
    }

}
