package service.account;

import model.account.Account;
import org.springframework.stereotype.Service;
import repository.account.AdminRepository;
import repository.account.CustomerRepository;
import repository.account.ParkingAgentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    AdminRepository adminRepository;
    CustomerRepository customerRepository;
    ParkingAgentRepository parkingAgentRepository;

    //add spot
    //add agent
    //add / modify rate
    //add entry / exit panel
    //update account

    //view account
    public List<Account> getAllAccounts(){

        List<Account> allAccounts = new ArrayList<>();

        allAccounts.add((Account) adminRepository.findAll());
        allAccounts.add((Account) customerRepository.findAll());
        allAccounts.add((Account) parkingAgentRepository.findAll());

        return allAccounts;

    }

}
