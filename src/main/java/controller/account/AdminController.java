package controller.account;

import model.account.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.account.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account/admin")
public class AdminController {

      private AdminService adminService;
//
//    @GetMapping
//    public Optional<Account> viewAccount(@PathVariable Integer id){
//       return accountService.getAccountById(id);
//    }
//
//    //add spot
//    //add agent
//    //add / modify rate
//    //add entry / exit panel
//    //update account
      //view account

    @GetMapping
    public List<Account> getAllAccounts(){
        return adminService.getAllAccounts();
    }
}
