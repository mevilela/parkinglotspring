package zely.parkinglotspring.service.parkingspot;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingspot.Compact;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import zely.parkinglotspring.model.parkingspot.Handicapped;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.model.vehicle.Car;

import zely.parkinglotspring.repository.parkinglot.ParkingLotRepository;
import zely.parkinglotspring.repository.parkingspot.ParkingSpotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkingSpotServiceTest {

    @Mock
    ParkingSpotRepository parkingSpotRepository;
    @Mock
    ParkingLotRepository parkingLotRepository;

    @InjectMocks
    ParkingSpotService parkingSpotService;



    @Test
    void getAllParkingSpots() {
        Compact compact = new Compact();
        Car car = new Car();
        compact.setVehicle(car);

        //given
        List<ParkingSpot> spotList = new ArrayList<>();
        spotList.add(compact);
        given(parkingSpotRepository.findAll()).willReturn(spotList);

        //when
        List<ParkingSpot> spots = parkingSpotService.getAllParkingSpots();

        //then
        assertThat(spotList).isEqualTo(spots);
        assertThat(spots).hasSize(1);
    }

    @Test
    void createNewParkingSpot() {
        ParkingLot parkingLot = new ParkingLot();
        Compact compact = new Compact();
        compact.setParkingLot(parkingLot);

        //given
        given(parkingLotRepository.findById(1)).willReturn(Optional.of(parkingLot));

        given(parkingSpotRepository.save(compact)).willReturn(compact);

        //when
        ParkingSpot newParkingSpot = parkingSpotService.createNewParkingSpot(compact);

        //then
        assertThat(parkingLot).isEqualTo(compact.getParkingLot());
        verify(parkingSpotRepository).save(compact);


    }
    @Test
    void when_parkingLotIsNull_createNewParkingSpot_willReturn_throwException() {

        assertThrows(EntityNotFoundException.class, () -> {
            parkingSpotService.createNewParkingSpot(null);
        });
    }

    @Test
    void when_findParkingSpotById_willReturn_spotById() {

        Handicapped handicapped = new Handicapped();
        Integer id = handicapped.getId();

        given(parkingSpotRepository.findById(id)).willReturn(Optional.of(handicapped));

        //when
        Optional<ParkingSpot> spotOptional = parkingSpotService.findParkingSpotById(id);

        //then
        assertThat(spotOptional.isPresent());
        assertThat(spotOptional).isEqualTo(Optional.of(handicapped));

    }

    @Test
    void when_updateParkingSpot_willReturn_updatedSpot(){
        Handicapped handicapped = new Handicapped();

        //given

        given(parkingSpotRepository.save(handicapped)).willReturn(handicapped);

        //when
        Handicapped updatedHandicapped = (Handicapped) parkingSpotService.updateParkingSpot(handicapped);

        //then
        assertThat(updatedHandicapped).isNotNull();
        assertThat(updatedHandicapped).isEqualTo(handicapped);
        then(parkingSpotRepository).should().save(updatedHandicapped);

    }

    @Test
    void when_getParkingSpotBySpotType_willReturn_spotByType() {
        Compact compact = new Compact();
        List<ParkingSpot> spotsByType = new ArrayList<>();
        spotsByType.add(compact);

        //given
        given(parkingSpotRepository.getParkingSpotsBySpotType(compact.getClass())).willReturn(spotsByType);

        //when
        List<ParkingSpot> result = parkingSpotService.getParkingSpotBySpotType(compact);

        //then
        assertThat(result).isEqualTo(spotsByType);
        verify(parkingSpotRepository).getParkingSpotsBySpotType(Compact.class);
    }

    @Test
    void when_countOccupiedSpotTypes_willReturn_occupiedSpotsByType() {
        Compact compact = new Compact();
        compact.setFree(false);

        //given
        given(parkingSpotRepository.countOccupiedSpotsByType(compact.getClass())).willReturn(1L);

        //when
        Long result = parkingSpotService.countOccupiedSpotTypes(Compact.class);

        //then
        assertThat(result).isEqualTo(1L);
        verify(parkingSpotRepository).countOccupiedSpotsByType(Compact.class);

    }

    @Test
    void when_countFreeSpotTypes_willReturn_freeSpotsByType() {
        Compact compact = new Compact();
        compact.setFree(true);

        //given
        given(parkingSpotRepository.countFreeSpotsByType(compact.getClass())).willReturn(1L);

        //when
        Long result = parkingSpotService.countFreeSpotTypes(Compact.class);

        //then
        assertThat(result).isEqualTo(1L);
        verify(parkingSpotRepository).countFreeSpotsByType(Compact.class);

    }

    @Test
    void when_countTotalSpotsByType_willReturn_totalSpotsByType() {
        Compact compact = new Compact();

        //given
        given(parkingSpotRepository.countTotalSpotsByType(compact.getClass())).willReturn(1L);

        //when
        Long result = parkingSpotService.countTotalSpotsByType(Compact.class);

        //then
        assertThat(result).isEqualTo(1L);
        verify(parkingSpotRepository).countTotalSpotsByType(Compact.class);
    }
}