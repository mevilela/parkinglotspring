package zely.parkinglotspring.manager;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.SetParkingRateDto;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.service.parkingrate.ParkingRateService;
import zely.parkinglotspring.service.parkingticket.ParkingTicketService;

import java.util.List;

@Service
public class ParkingRateManager {

    ParkingRateService parkingRateService;

    ParkingTicketService parkingTicketService;

    public ParkingRateManager(ParkingRateService parkingRateService, ParkingTicketService parkingTicketService) {
        this.parkingRateService = parkingRateService;
        this.parkingTicketService = parkingTicketService;
    }

    public ParkingRate createParkingRate(SetParkingRateDto parkingRateDto){

        double rateValue = parkingRateDto.getRate();


        ParkingRate parkingRateByType = switch (parkingRateDto.getVehicleType()) {
            case "moto" -> new ParkingRate(rateValue, "moto");
            case "car" -> new ParkingRate(rateValue, "car");
            case "van" -> new ParkingRate(rateValue, "van" );
            case "truck" -> new ParkingRate(rateValue, "truck");
            default -> throw new IllegalArgumentException("Invalid vehicle type provided");
        };

        parkingRateService.setParkingRate(parkingRateByType);

        return parkingRateByType;

    }
}
