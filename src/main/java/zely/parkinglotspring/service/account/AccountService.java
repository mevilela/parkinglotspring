package zely.parkinglotspring.service.account;

import zely.parkinglotspring.model.account.Account;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Customer;
import zely.parkinglotspring.model.account.ParkingAgent;
import zely.parkinglotspring.repository.account.AccountRepository;

import java.util.List;

import static zely.parkinglotspring.model.account.AccountType.*;

@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    public List<Account> getAllAccounts(){

        return (List<Account>) accountRepository.findAll();

    }


//    public Account newAccount(Object account) {
//        if (account instanceof Admin) {
//            Admin admin = (Admin) account;
//            return accountRepository.save(admin);
//        } else if (account instanceof ParkingAgent){
//            ParkingAgent parkingAgent = (ParkingAgent) account;
//            return accountRepository.save(parkingAgent);
//        } else if (account instanceof Customer){
//            Customer customer = (Customer) account;
//            return accountRepository.save(customer);
//        } else {
//            throw new IllegalArgumentException("Invalid class account");
//        }
//    }

    public Account newAccount(Object accountType) {
        if (accountType.equals(ADMIN)) {
            Admin admin = new Admin();
            return accountRepository.save(admin);
        } else if (accountType.equals(PARKING_AGENT)){
            ParkingAgent parkingAgent = new ParkingAgent();
            return accountRepository.save(parkingAgent);
        } else if (accountType.equals(CUSTOMER)){
            Customer customer = new Customer();
            return accountRepository.save(customer);
        }
        else {
            throw new IllegalArgumentException("Invalid account type");
        }
    }
}

//add spot
//add agent
//add / modify rate
//add entry / exit panel
//update account

//view account
