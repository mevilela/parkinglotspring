package zely.parkinglotspring.service.account;

import zely.parkinglotspring.model.account.*;
import org.springframework.stereotype.Service;
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


    public Account newAccount(Account account) {
        if (account instanceof Admin) {
            return accountRepository.save(new Admin());
        } else if (account instanceof ParkingAgent){
            return accountRepository.save(new ParkingAgent());
        } else if (account instanceof Customer){
            return accountRepository.save(new Customer());
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccountById(Integer id, Account account) {

        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account ID not found"));

        if (existingAccount instanceof Admin && account instanceof Admin) {
            Admin existingAdmin = (Admin) existingAccount;
            Admin updatedAdmin = (Admin) account;

            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            existingAdmin.setPerson(updatedAdmin.getPerson());
            existingAdmin.setStatus(updatedAdmin.getStatus());

            return accountRepository.save(existingAdmin);

        } else if (existingAccount instanceof Customer && account instanceof Customer) {
            Customer existingCustomer = (Customer) existingAccount;
            Customer updatedCustomer = (Customer) account;

            existingCustomer.setUsername(updatedCustomer.getUsername());
            existingCustomer.setPassword(updatedCustomer.getPassword());
            existingCustomer.setPerson(updatedCustomer.getPerson());
            existingCustomer.setStatus(updatedCustomer.getStatus());

            return accountRepository.save(existingCustomer);

        } else if (existingAccount instanceof ParkingAgent && account instanceof ParkingAgent) {
            ParkingAgent existingParkingAgent = (ParkingAgent) existingAccount;
            ParkingAgent updatedParkingAgent = (ParkingAgent) account;

            existingParkingAgent.setUsername(updatedParkingAgent.getUsername());
            existingParkingAgent.setPassword(updatedParkingAgent.getPassword());
            existingParkingAgent.setPerson(updatedParkingAgent.getPerson());
            existingParkingAgent.setStatus(updatedParkingAgent.getStatus());

            return accountRepository.save(existingParkingAgent);

        }
        throw new RuntimeException("Invalid Account");
    }
}

//add spot
//add agent
//add / modify rate
//add entry / exit panel
//update account

//view account
