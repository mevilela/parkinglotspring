package zely.parkinglotspring.controller.account;

import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.account.*;
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

    @PostMapping("/create")
    public Account newAccount(@RequestBody Object accountType){
        return accountService.newAccount(accountType);

    }

//    @PostMapping("/create/admin")
//    public Account newAdminAccount(@RequestBody Admin admin){
//
//        return accountService.newAdminAccount(admin);
//
//    }
//
//    @PostMapping("/create/agent")
//    public Account newParkingAgentAccount(@RequestBody ParkingAgent parkingAgent){
//
//        return accountService.newParkingAgentAccount(parkingAgent);
//
//    }
//
//    @PostMapping("/create/customer")
//    public Account newCustomerAccount(@RequestBody Customer customer){
//
//        return accountService.newCustomerAccount(customer);
//    }





}

// admin
////    //add spot
////    //add agent
////    //add / modify rate
////    //add entry / exit panel
////    //update account
//      //view account

// customer
//    //take ticket
//    //scan ticket
//    //pay ticket
//        //cash
//        //credit
//    //park vehicle

//parkingAgent
////    /* update account
////     * login / logout
////     * view account
////     * take ticket
////     * scan ticket
////     * pay ticket
////     *   *cash
////     *   *credit card
////     * park vehicle
////     */
