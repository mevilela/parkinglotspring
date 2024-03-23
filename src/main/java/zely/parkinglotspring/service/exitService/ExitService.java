package zely.parkinglotspring.service.exitService;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.model.exit.Exit;
import zely.parkinglotspring.repository.exit.ExitRepository;

@Service
public class ExitService {

    private final ExitRepository exitRepository;

    public ExitService(ExitRepository exitRepository) {
        this.exitRepository = exitRepository;
    }


    public Exit createExit(Exit exit) {
            return exitRepository.save(exit);
        }
}
