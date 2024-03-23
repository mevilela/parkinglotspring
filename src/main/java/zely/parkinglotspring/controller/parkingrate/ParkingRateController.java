package zely.parkinglotspring.controller.parkingrate;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.dto.SetParkingRateDto;
import zely.parkinglotspring.manager.ParkingRateManager;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.service.parkingrate.ParkingRateService;

import java.util.List;

@RestController
@RequestMapping("/parking-rate")
public class ParkingRateController {

    ParkingRateManager parkingRateManager;
    ParkingRateService parkingRateService;

    public ParkingRateController(ParkingRateManager parkingRateManager, ParkingRateService parkingRateService) {
        this.parkingRateManager = parkingRateManager;
        this.parkingRateService = parkingRateService;
    }

    @PostMapping("/") //todo apenas adm
    public ResponseEntity<ParkingRate> CreateParkingRate(@RequestBody SetParkingRateDto parkingRateDto){

        ParkingRate newParkingRate = parkingRateManager.createParkingRate(parkingRateDto);

        return new ResponseEntity<>(newParkingRate, HttpStatus.CREATED);

    }

    @GetMapping("/rates")
    public ResponseEntity<List<ParkingRate>> getAllParkingRates() {
        List<ParkingRate> parkingRates = parkingRateService.getAllParkingRates();
        return ResponseEntity.ok(parkingRates);
    }
}
