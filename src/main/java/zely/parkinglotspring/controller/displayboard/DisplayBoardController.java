package zely.parkinglotspring.controller.displayboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.parkinglotspring.dto.DisplayBoardDto;
import zely.parkinglotspring.manager.DisplayBoardManager;

@RestController
@RequestMapping("/display-board")
public class DisplayBoardController {

    private final DisplayBoardManager displayBoardManager;


    public DisplayBoardController(DisplayBoardManager displayBoardManager) {
        this.displayBoardManager = displayBoardManager;
    }


    @GetMapping("/")
    public ResponseEntity<DisplayBoardDto> showSpotsOnDisplayBoard(){
        return ResponseEntity.ok(displayBoardManager.showSpotsOnDisplayBoard());
    }

}
