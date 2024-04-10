package zely.parkinglotspring.service.entrance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.repository.entrance.EntranceRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EntranceServiceTest {

    @Mock
    EntranceRepository entranceRepository;

    @InjectMocks
    EntranceService entranceService;

    @Test
    void when_createExit_willReturn_newExit() {

        Entrance entrance = new Entrance();

        given(entranceRepository.save(entrance)).willReturn(entrance);

        Entrance newEntrance = entranceService.createEntrance(entrance);

        assertThat(newEntrance).isEqualTo(entrance);


    }

}