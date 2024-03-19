package zely.parkinglotspring.service;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.repository.entrance.EntranceRepository;

@Service
public class EntranceService {

    private final EntranceRepository entranceRepository;

    public EntranceService(EntranceRepository entranceRepository) {
        this.entranceRepository = entranceRepository;
    }

    public Entrance createEntrance(Entrance entrance) {
        return entranceRepository.save(entrance);
    }
}
