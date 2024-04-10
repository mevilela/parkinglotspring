package zely.parkinglotspring.service.parkingrate;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.repository.parkingrate.ParkingRateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingRateService {

    ParkingRateRepository parkingRateRepository;

    public ParkingRateService(ParkingRateRepository parkingRateRepository) {
        this.parkingRateRepository = parkingRateRepository;
    }

    public ParkingRate defineParkingRate(ParkingRate parkingRate){

        return parkingRateRepository.save(parkingRate);
    }

    public Optional<ParkingRate> getParkingRateById(Integer id){
        return parkingRateRepository.findById(id);
    }

    public ParkingRate getParkingRateByVehicleType(String vehicleType){
        return parkingRateRepository.findParkingRateByVehicleType(vehicleType);
    }

    public List<ParkingRate> getAllParkingRates(){
        return (List<ParkingRate>) parkingRateRepository.findAll();
    }



}
