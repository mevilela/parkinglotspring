package zely.parkinglotspring;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Person;
import zely.parkinglotspring.model.address.Address;
import zely.parkinglotspring.model.displayboard.DisplayBoard;
import zely.parkinglotspring.model.entrance.Entrance;
import zely.parkinglotspring.model.exit.Exit;
import zely.parkinglotspring.model.parkinglot.ParkingLot;
import zely.parkinglotspring.model.parkingrate.ParkingRate;
import zely.parkinglotspring.model.parkingspot.*;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;
import zely.parkinglotspring.model.payment.Cash;
import zely.parkinglotspring.model.payment.Payment;
import zely.parkinglotspring.model.vehicle.*;
import zely.parkinglotspring.repository.displayboard.DisplayBoardRepository;
import zely.parkinglotspring.repository.entrance.EntranceRepository;
import zely.parkinglotspring.repository.exit.ExitRepository;
import zely.parkinglotspring.repository.parkingrate.ParkingRateRepository;
import zely.parkinglotspring.repository.payment.PaymentRepository;
import zely.parkinglotspring.repository.account.AccountRepository;
import zely.parkinglotspring.repository.parkinglot.ParkingLotRepository;
import zely.parkinglotspring.repository.parkingspot.ParkingSpotRepository;
import zely.parkinglotspring.repository.parkingticket.ParkingTicketRepository;
import zely.parkinglotspring.repository.vehicle.VehicleRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static zely.parkinglotspring.model.account.AccountStatus.*;
import static zely.parkinglotspring.model.payment.PaymentStatus.COMPLETED;

@SpringBootApplication
public class ParkinglotspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkinglotspringApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(AccountRepository accountRepository,
                                        ParkingLotRepository parkingLotRepository,
                                        ParkingSpotRepository parkingSpotRepository,
                                        VehicleRepository vehicleRepository,
                                        ParkingTicketRepository parkingTicketRepository,
                                        PaymentRepository paymentRepository,
                                        EntranceRepository entranceRepository,
                                        ExitRepository exitRepository,
                                        DisplayBoardRepository displayBoardRepository,
                                        ParkingRateRepository parkingRateRepository){
        return args -> {

            Faker faker = new Faker();

            createAdminUser(accountRepository, faker);

            ParkingLot parkingLot = createParkingLot(parkingLotRepository, faker);

            //setting DisplayBoard
            createDisplayBoard(displayBoardRepository, parkingLot);

            createParkingSpots(parkingSpotRepository, parkingLot);

            Vehicle vehicle = createFakeVehicle(vehicleRepository, faker);

            findFreeCompactSpotAndParkVehicle(parkingSpotRepository, vehicleRepository, vehicle);

            ParkingRate parkingRate = new ParkingRate(2.0, parkingLot);
            parkingRateRepository.save(parkingRate);


            //creating ticket
            ParkingTicket ticket = new ParkingTicket();
            //creating payment method fot the ticket
            Payment cashPaidTicket = new Cash();
            //saving payment method
            ticket.setPayment(cashPaidTicket);
            //setting ticket
            ticket.setParkingRate(parkingRate);
            //setting timestamp
            ticket.setTimestamp(LocalDateTime.of(2024,03,12,14,00,00));
           // vehicle.assignTicket(ticket);
            vehicle.assignTicket(ticket);

            //saving ticket
            parkingTicketRepository.save(ticket);

            //Adding an Entrance
            Entrance entrance = new Entrance();
            //Adding a ticket for the getTicket method
            entrance.getTickets().add(ticket);
            //Setting an Entrance for the ticket
            ticket.setEntrance(entrance);

            //saving Entrance
            entranceRepository.save(entrance);

            ticket.setExitTime(LocalDateTime.of(2024,03,12,21,00,00));

            //setting a ticket fot the payment method
            cashPaidTicket.setParkingTicket(ticket);

            //setting payment amount
            double parkingHours = ticket.getTimestamp().until(ticket.getExitTime(), ChronoUnit.HOURS);

            double amount = parkingHours * ticket.getParkingRate().getParkingRate();

            ticket.setAmount(amount);

            parkingTicketRepository.save(ticket);

            //setting payment status
            cashPaidTicket.setPaymentStatus(COMPLETED);

            //saving payment
            paymentRepository.save(cashPaidTicket);

            //adding Exit
            Exit exit = new Exit();
            //adding a ticket for the exit method
            exit.getTickets().add(ticket);
            //setting an exit for the ticket
            ticket.setExit(exit);

            //saving exit
            exitRepository.save(exit);

            //saving ticket
            parkingTicketRepository.save(ticket);


            List<ParkingSpot> parkingSpotList = (List<ParkingSpot>) parkingSpotRepository.findAll();
            Map<String, Long> freeSpotsByType = new HashMap<>();

            //counting freeSpots by type
            Long compacts = parkingSpotRepository.getParkingSpotsBySpotType(Compact.class).stream().count();
            Long handicappeds = parkingSpotRepository.getParkingSpotsBySpotType(Handicapped.class).stream().count();
            Long larges = parkingSpotRepository.getParkingSpotsBySpotType(Large.class).stream().count();
            Long motos = parkingSpotRepository.getParkingSpotsBySpotType(Motorcycle.class).stream().count();

            //adding spots
            freeSpotsByType.put("Compact", compacts);
            freeSpotsByType.put("Handicapped", handicappeds);
            freeSpotsByType.put("Large", larges);
            freeSpotsByType.put("Motorcycle", motos);

        };
    }


    private static void createParkingSpots(ParkingSpotRepository parkingSpotRepository, ParkingLot parkingLot) {
        createParkingSpotBySpotType(parkingSpotRepository, parkingLot, 2, "handicapped");

        createParkingSpotBySpotType(parkingSpotRepository, parkingLot, 3, "motorcycle");

        createParkingSpotBySpotType(parkingSpotRepository, parkingLot, 2, "large");

        createParkingSpotBySpotType(parkingSpotRepository, parkingLot, 3, "compact");
    }

    private static void createDisplayBoard(DisplayBoardRepository displayBoardRepository, ParkingLot parkingLot) {
        DisplayBoard displayBoard = new DisplayBoard();
        displayBoard.setParkingLot(parkingLot);
        parkingLot.setDisplayBoard(displayBoard);
        displayBoardRepository.save(displayBoard);
    }

    private static void findFreeCompactSpotAndParkVehicle(ParkingSpotRepository parkingSpotRepository, VehicleRepository vehicleRepository, Vehicle vehicle) {
        List<ParkingSpot> freeCompactParkingSpots = parkingSpotRepository.getParkingSpotsBySpotType(Compact.class);
        if(!freeCompactParkingSpots.isEmpty()) {
            ParkingSpot freeCompactParkingSpot = freeCompactParkingSpots.get(0);
            vehicle.setParkingSpot(freeCompactParkingSpot);
            vehicleRepository.save(vehicle);
            freeCompactParkingSpot.setFree(false);
            parkingSpotRepository.save(freeCompactParkingSpot);

        } else {
            throw new RuntimeException("ParkingSpot not avaiable");
        }
    }

    private static Vehicle createFakeVehicle(VehicleRepository vehicleRepository, Faker faker) {
        String randomLicenseNumber = "###-###";
        Vehicle vehicle = new Car();
        vehicle.setLicenseNo(faker.numerify(randomLicenseNumber));
        vehicle = vehicleRepository.save(vehicle);
        return vehicle;
    }

//            createVehicle(vehicleRepository, 4, "car", faker);
//            createVehicle(vehicleRepository, 3, "truck", faker);
//            createVehicle(vehicleRepository, 2, "van", faker);
//            createVehicle(vehicleRepository, 2, "moto", faker);


    private static void createVehicle(VehicleRepository vehicleRepository, Integer numberOfVehicles, String vehicleType, Faker faker) {
        Vehicle vehicle = null;
        String randomLicenseNumber = "###-###";
        for (int i = 0; i < numberOfVehicles; i++){
            if (vehicleType.equals("car")){
                vehicle = new Car();
                vehicle.setLicenseNo(faker.numerify(randomLicenseNumber));
            } else if (vehicleType.equals("moto")){
                vehicle = new Moto();
                vehicle.setLicenseNo(faker.numerify(randomLicenseNumber));
            } else if (vehicleType.equals("van")){
                vehicle = new Van();
                vehicle.setLicenseNo(faker.numerify(randomLicenseNumber));
            } else if (vehicleType.equals("truck")){
                vehicle = new Truck();
                vehicle.setLicenseNo(faker.numerify(randomLicenseNumber));
            } else {
                throw new RuntimeException("Vehicle type not valid");
            }
            vehicleRepository.save(vehicle);
        }
    }

    private static void createParkingSpotBySpotType(ParkingSpotRepository parkingSpotRepository,
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
