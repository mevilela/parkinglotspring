package zely.parkinglotspring.controller.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zely.parkinglotspring.model.account.Account;
import zely.parkinglotspring.model.account.Customer;
import zely.parkinglotspring.service.account.AccountService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    private AccountService accountService;
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        accountController = new AccountController(accountService);
    }

    @Test
    void testGetAllAccounts() {
        Account customer1 = mock(Customer.class);
        Account customer2 = mock(Customer.class);
        List<Account> accounts = Arrays.asList(customer1, customer2);

        when(accountService.getAllAccounts()).thenReturn(accounts);

        ResponseEntity<List<Account>> response = accountController.getAllAccounts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void testNewAccount() {
        Account customer1 = mock(Customer.class);

        when(accountService.newAccount(customer1)).thenReturn(customer1);

        ResponseEntity<Account> response = accountController.newAccount(customer1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer1, response.getBody());
        verify(accountService, times(1)).newAccount(customer1);
    }

    @Test
    void testUpdateAccount() {
        int id = 1;
        Account updatedAccount = mock(Customer.class);

        when(accountService.updateAccountById(id, updatedAccount)).thenReturn(updatedAccount);

        ResponseEntity<Account> response = accountController.updateAccount(id, updatedAccount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAccount, response.getBody());
        verify(accountService, times(1)).updateAccountById(id, updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id){

        accountService.deleteAccountById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Test
    void testDeleteAccount(){

        int id = 1;

        doNothing().when(accountService).deleteAccountById(id);


        ResponseEntity<Void> response = accountController.deleteAccount(id);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accountService, times(1)).deleteAccountById(id);

    }

}
