package zely.parkinglotspring.service.parkingrate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.vehicle.Car;
import zely.parkinglotspring.repository.parkingrate.ParkingRateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ParkingRateServiceTest {

    @Mock
    ParkingRateRepository parkingRateRepository;

    @InjectMocks
    ParkingRateService parkingRateService;


    @Test
    void when_defineParkingRate_willReturn_newPArkingRate() {
        ParkingRate parkingRate = new ParkingRate();
        //given
        given(parkingRateRepository.save(parkingRate)).willReturn(parkingRate);

        //when
        ParkingRate newParkingRate = parkingRateService.defineParkingRate(parkingRate);

        //then
        assertThat(newParkingRate).isEqualTo(parkingRate);
    }


    @Test
    void when_getParkingRateByVehicleType_willReturn_rateByVehicleType() {
        ParkingRate parkingRate = new ParkingRate();
        parkingRate.setVehicleType("car");
        given(parkingRateRepository.findParkingRateByVehicleType("car")).willReturn(parkingRate);

        //when
        ParkingRate foundRate = parkingRateService.getParkingRateByVehicleType("car");

        //then
        assertThat(parkingRate).isEqualTo(foundRate);
    }

    @Test
    void when_getAllParkingRates_willReturn_findAllRates() {
        ParkingRate parkingRate = new ParkingRate();
        List<ParkingRate> rates = new ArrayList<>();
        rates.add(parkingRate);

        //given
        given(parkingRateRepository.findAll()).willReturn(rates);

        //when
        List<ParkingRate> result = parkingRateService.getAllParkingRates();

        //then
        assertThat(result).isEqualTo(rates);


    }

    @Test
    void when_testGetParkingRateById_willReturn_parkingRateById() {
        ParkingRate parkingRate = new ParkingRate();
        Integer id = parkingRate.getId();

        //given
        given(parkingRateRepository.findById(id)).willReturn(Optional.of(parkingRate));

        //when
        Optional<ParkingRate> rateOptional = parkingRateService.parkingRateRepository.findById(id);

        //then
        assertThat(rateOptional).isEqualTo(Optional.of(parkingRate));
    }
}