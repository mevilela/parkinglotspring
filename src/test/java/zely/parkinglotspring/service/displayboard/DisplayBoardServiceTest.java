package zely.parkinglotspring.service.displayboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.displayboard.DisplayBoard;
import zely.parkinglotspring.repository.displayboard.DisplayBoardRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DisplayBoardServiceTest {

    @Mock
    DisplayBoardRepository displayBoardRepository;
    @InjectMocks
    DisplayBoardService displayBoardService;


    @Test
    void when_getDisplayBoardById_willReturn_findDisplayBoardById() {
        DisplayBoard displayBoard = new DisplayBoard();
        Integer id = displayBoard.getId();

        given(displayBoardRepository.findById(id)).willReturn(Optional.of(displayBoard));

        //when
        Optional<DisplayBoard> displayBoardOptional = displayBoardService.getDisplayBoardById(id);

        //then
        assertThat(displayBoardOptional.isPresent());
        assertThat(Optional.of(displayBoard)).isEqualTo(displayBoardOptional);

    }

    @Test
    void when_createDisplayBoard_willReturn_newDisplayBoard() {
        DisplayBoard displayBoard = new DisplayBoard();

        given(displayBoardRepository.save(displayBoard)).willReturn(displayBoard);

        //when
        DisplayBoard newDisplayBoard = displayBoardService.createDisplayBoard(displayBoard);

        //then
        assertThat(displayBoard).isEqualTo(newDisplayBoard);


    }
}