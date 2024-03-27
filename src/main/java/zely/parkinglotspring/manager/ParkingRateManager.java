package zely.parkinglotspring.manager;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.CreateParkingRateDto;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.service.parkingrate.ParkingRateService;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;

@Service
public class ParkingRateManager {

    private final ParkingRateService parkingRateService;

    public ParkingRateManager(ParkingRateService parkingRateService) {
        this.parkingRateService = parkingRateService;
    }

    public ParkingRate createParkingRate(CreateParkingRateDto parkingRateDto){

        double rateValue = parkingRateDto.getRate();


        ParkingRate parkingRateByType = switch (parkingRateDto.getVehicleType()) {
            case "moto" -> new ParkingRate(rateValue, "moto");
            case "car" -> new ParkingRate(rateValue, "car");
            case "van" -> new ParkingRate(rateValue, "van" );
            case "truck" -> new ParkingRate(rateValue, "truck");
            default -> throw new IllegalArgumentException("Invalid vehicle type provided");
        };

        ParkingRate test = new ParkingRate(rateValue, parkingRateDto.getVehicleType());

        parkingRateService.setParkingRate(parkingRateByType);

        return parkingRateByType;

    }
}
