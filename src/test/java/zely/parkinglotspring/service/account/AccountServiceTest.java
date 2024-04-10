package zely.parkinglotspring.service.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.account.Account;
import zely.parkinglotspring.model.account.Admin;
import zely.parkinglotspring.model.account.Customer;
import zely.parkinglotspring.model.account.ParkingAgent;
import zely.parkinglotspring.repository.account.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountService accountService;


    @Test
    void getAllAccounts() {
        Admin admin = new Admin();
        ParkingAgent agent = new ParkingAgent();
        List<Account> accounts = new ArrayList<>();
        accounts.add(admin);
        accounts.add(agent);

        given(accountRepository.findAll()).willReturn(accounts);

        //when
        List<Account> result = accountService.getAllAccounts();

        //then
        assertThat(result).isEqualTo(accounts);
    }

    @Test
    void when_newAccount_willReturn_instanceOfNewAccount() {
        Admin admin = new Admin();

        given(accountRepository.save(any(Admin.class))).willReturn(admin);

        //when
        Account newAdmin = accountService.newAccount(admin);

        //then
        assertThat(newAdmin).isEqualTo(admin);

    }

    @Test
    void deleteAccountById() {
        Integer id = 1;

        accountService.deleteAccountById(id);

        verify(accountRepository).deleteById(id);
    }

    @Test
    void updateAccountById() {
        Admin admin = new Admin();
        admin.setUsername("admin1");
        admin.setPassword("123");
        Integer id = admin.getId();

        given(accountRepository.findById(id)).willReturn(Optional.of(admin));
        given(accountRepository.save(admin)).willReturn(admin);


        Admin updatedAdmin = new Admin();
        updatedAdmin.setUsername("admin123");
        updatedAdmin.setPassword("123123");

        Account result = accountService.updateAccountById(id, updatedAdmin);

        //then
        verify(accountRepository).findById(id);

        assertThat(result.getPassword()).isEqualTo(updatedAdmin.getPassword());
        assertThat(result.getUsername()).isEqualTo(updatedAdmin.getUsername());

    }

    @Test
    void when_updateAInvalidAccount_willThrow_runTimeException() {
        Integer accountId = 1;
        Admin updatedAdmin = new Admin();

        given(accountRepository.findById(accountId)).willReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            accountService.updateAccountById(accountId, updatedAdmin);
        });
    }
}