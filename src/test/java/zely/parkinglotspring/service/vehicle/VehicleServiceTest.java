package zely.parkinglotspring.service.vehicle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.vehicle.Car;
import zely.parkinglotspring.model.vehicle.Van;
import zely.parkinglotspring.model.vehicle.Vehicle;
import zely.parkinglotspring.repository.vehicle.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleService vehicleService;

    @Test
    void when_getAllVehicles_willReturn_vehicles() {
        //given
        Van van = new Van();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(van);

        given(vehicleRepository.findAll()).willReturn(vehicles);

        //when
        List<Vehicle> allVehiclesList = vehicleService.getAllVehicles();

        //then
        then(vehicleRepository).should().findAll();

        assertThat(allVehiclesList).hasSize(1);
    }

    @Test
    void when_createNewVehicle_willReturn_newVehicle() {
        //given
        Van van = new Van();

        given(vehicleRepository.save(any(Vehicle.class))).willReturn(van);

        //when
        Vehicle savedVehicle = vehicleService.createNewVehicle(van);

        //then
        assertThat(savedVehicle).isNotNull();
        assertThat(savedVehicle).isEqualTo(van);
        assertThat(savedVehicle).isSameAs(van);

        then(vehicleRepository).should().save(van);

    }


    @Test
    void when_findByLicenseNo_willReturn_vehicleByLicenseNo() {
        //given
        Car expectCar = new Car();
        String licenseNo = "12AB34";
        expectCar.setLicenseNo(licenseNo);

        given(vehicleRepository.findVehicleByLicenseNo(licenseNo)).willReturn(expectCar);

        //when
        vehicleService.findByLicenseNo(licenseNo);

        //then
        assertThat(licenseNo).isEqualTo(expectCar.getLicenseNo());

    }

    @Test
    void when_findByLicenseNo_isNull_willReturn_ThrowException() {

        assertThrows(NullPointerException.class, () -> {
            vehicleService.findByLicenseNo(null);
        });
    }

    @Test
    void when_findById_willReturn_vehicle() {
        //given
        Car car = new Car();
        Integer id = car.getId();
        given(vehicleRepository.findById(id)).willReturn(Optional.of(car));

        //when
        Optional<Vehicle> foundCarOptional = vehicleService.findById(id);

        //then
        assertThat(foundCarOptional).isPresent();
        Vehicle foundCar = foundCarOptional.get();
        assertThat(foundCar).isInstanceOf(Car.class);
    }
}

