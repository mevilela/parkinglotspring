package zely.parkinglotspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Person;
import zely.parkinglotspring.repository.account.AccountRepository;

import static zely.parkinglotspring.model.account.AccountStatus.*;

@SpringBootApplication
public class ParkinglotspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkinglotspringApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(AccountRepository accountRepository) {
        return args -> {

            Person person1 = new Person();
            person1.setName("Maria");
            person1.setEmail("maria@gmail.com");
            person1.setAddress("Rua das Flores, 9");
            person1.setPhone("963321321");


            Admin admin1 = new Admin();
            admin1.setId(1);
            admin1.setPerson(person1);
            admin1.setPassword("123456");
            admin1.setUsername("maria");
            admin1.setStatus(CANCELED);

            accountRepository.save(admin1);
        };
    }
}
