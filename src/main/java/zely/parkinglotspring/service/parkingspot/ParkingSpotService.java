package zely.parkinglotspring.service.parkingspot;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingspot.*;
import zely.parkinglotspring.repository.parkinglot.ParkingLotRepository;
import zely.parkinglotspring.repository.parkingspot.ParkingSpotRepository;

import java.util.List;

@Service
public class ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingLotRepository parkingLotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, ParkingLotRepository parkingLotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingSpot> getAllParkingSpots() {
       return (List<ParkingSpot>) parkingSpotRepository.findAll();
    }

    public ParkingSpot createNewParkingSpot(ParkingSpot parkingSpot) {

        ParkingLot defaultParkingLot = parkingLotRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("ParkingLot id not found"));

        parkingSpot.setParkingLot(defaultParkingLot);

        return parkingSpotRepository.save(parkingSpot);

    }
}
