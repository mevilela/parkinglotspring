package zely.parkinglotspring.service.exitService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.exit.Exit;
import zely.parkinglotspring.repository.exit.ExitRepository;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ExitServiceTest {

    @Mock
    ExitRepository exitRepository;

    @InjectMocks
    ExitService exitService;

    @Test
    void when_createExit_willReturn_newExit() {

        Exit exit = new Exit();

        given(exitRepository.save(exit)).willReturn(exit);

        Exit newExit = exitService.createExit(exit);

        assertThat(newExit).isEqualTo(exit);




    }
}