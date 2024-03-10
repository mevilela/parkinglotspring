package zely.parkinglotspring;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Person;
import zely.parkinglotspring.model.address.Address;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingspot.*;
import zely.parkinglotspring.repository.account.AccountRepository;
import zely.parkinglotspring.repository.parkinglot.ParkingLotRepository;
import zely.parkinglotspring.repository.parkingspot.ParkingSpotRepository;

import static zely.parkinglotspring.model.account.AccountStatus.*;

@SpringBootApplication
public class ParkinglotspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkinglotspringApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(AccountRepository accountRepository,
                                        ParkingLotRepository parkingLotRepository,
                                        ParkingSpotRepository parkingSpotRepository) {
        return args -> {

            Faker faker = new Faker();

            createAdminUser(accountRepository, faker);

            ParkingLot parkingLot = createParkingLot(parkingLotRepository, faker);

            createParkingSpot(parkingSpotRepository, parkingLot, 2, "handicapped");

            createParkingSpot(parkingSpotRepository, parkingLot, 3, "motorcycle");

            createParkingSpot(parkingSpotRepository, parkingLot, 2, "large");

            createParkingSpot(parkingSpotRepository, parkingLot, 3, "compact");

        };
    }

    private static void createParkingSpot(ParkingSpotRepository parkingSpotRepository,
                                          ParkingLot parkingLot,
                                          Integer numberOfSpots, String spotType) {
        ParkingSpot parkingSpot = null;

        for(int i = 0; i < numberOfSpots; i++) {

            if (spotType.equals("handicapped")) {

                parkingSpot = new Handicapped();

            } else if (spotType.equals("compact")) {
                parkingSpot = new Compact();

            } else if (spotType.equals("large")) {
                parkingSpot = new Large();
            } else if (spotType.equals("motorcycle")) {

                parkingSpot = new Motorcycle();

            } else {
                throw new RuntimeException("SpotType not valid");
            }

            parkingSpot.setParkingLot(parkingLot);
            parkingSpotRepository.save(parkingSpot);
        }
    }

    private static Compact createCompactParkingSpot(ParkingLot parkingLot) {
        Compact compact = new Compact();
        compact.setParkingLot(parkingLot);
        return compact;
    }

    private static Handicapped createHandicappedParkingSpot(ParkingLot parkingLot) {
        Handicapped handicapped = new Handicapped();
        handicapped.setParkingLot(parkingLot);
        return handicapped;
    }


    private static ParkingLot createParkingLot(ParkingLotRepository parkingLotRepository, Faker faker) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Nice Parking");
        parkingLot.setAddress(createRandomAddress(faker));
        return  parkingLotRepository.save(parkingLot);
    }

    private static void createAdminUser(AccountRepository accountRepository, Faker faker) {
        Address address1 = createRandomAddress(faker);

        Person person1 = new Person();
        person1.setName("Maria");
        person1.setEmail("maria@gmail.com");
        person1.setAddress(address1);
        person1.setPhone("963321321");


        Admin admin1 = new Admin();
        admin1.setPerson(person1);
        admin1.setPassword("123456");
        admin1.setUsername("maria");
        admin1.setStatus(ACTIVE);

        accountRepository.save(admin1);
    }

    private static Address createRandomAddress(Faker faker) {
        Address address1 = new Address();
        address1.setStreet(faker.address().streetAddress());
        address1.setZipcode(faker.address().zipCode());
        address1.setCity(faker.address().cityName());
        address1.setState(faker.address().state());
        address1.setCountry(faker.address().country());
        return address1;
    }
}
