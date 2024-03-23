package zely.parkinglotspring.controller.account;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.account.*;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.service.account.AccountService;

import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/") //todo apenas admin pode criar
    public Account newAccount(@RequestBody Account account) {
        return accountService.newAccount(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer id, @RequestBody Account account){

        Account updatedAccount = accountService.updateAccountById(id, account);

        return new ResponseEntity<>(updatedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id){

        accountService.deleteAccountById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

// admin
////    //add spot
////    //add agent - done
////    //add / modify rate
////    //add entry / exit panel
////    //update account -
//      //view account - done -

// customer
//    //take ticket -
//    //scan ticket -
//    //pay ticket -
//        //cash -
//        //credit -
//    //park vehicle -

//parkingAgent
////    /* update account
////     * login / logout
////     * view account
////     * take ticket -
////     * scan ticket -
////     * pay ticket -
////     *   *cash -
////     *   *credit card -
////     * park vehicle -
////     */
