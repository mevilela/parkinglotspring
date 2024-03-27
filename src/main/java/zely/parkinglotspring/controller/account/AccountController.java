package zely.parkinglotspring.controller.account;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.account.*;
import zely.parkinglotspring.model.parkingspot.ParkingSpot;
import zely.parkinglotspring.service.account.AccountService;

import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //todo: Passar username como paramentro nos metodos q so o admin pode ter acesso
    //todo: criar um novo metodo no service "findAccountByUserName" e validar se é do tipo admin - se true: pode seguir, se false - exception.
    //todo: testes

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/") //todo apenas admin pode criar
    public ResponseEntity<Account> newAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.newAccount(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account account){

        Account updatedAccount = accountService.updateAccountById(id, account);

        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id){

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
