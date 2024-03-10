package zely.parkinglotspring.service.account;

import zely.parkinglotspring.model.account.Account;
import org.springframework.stereotype.Service;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Customer;
import zely.parkinglotspring.model.account.ParkingAgent;
import zely.parkinglotspring.repository.account.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    public List<Account> getAllAccounts(){

        return (List<Account>) accountRepository.findAll();

    }

    public Account newAdminAccount(Admin admin) {

        return accountRepository.save(admin);

    }

    public Account newParkingAgentAccount(ParkingAgent parkingAgent) {

        return accountRepository.save(parkingAgent);
    }

    public Account newCustomerAccount(Customer customer) {

        return accountRepository.save(customer);
    }
}

//add spot
//add agent
//add / modify rate
//add entry / exit panel
//update account

//view account
