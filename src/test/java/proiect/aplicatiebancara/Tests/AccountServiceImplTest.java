package proiect.aplicatiebancara.Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.model.Account;
import proiect.aplicatiebancara.repository.AccountRepository;
import proiect.aplicatiebancara.service.impl.AccountServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testCreateAccount() {

        AccountDto accountDto = new AccountDto(null, "John Doe", 100.0);
        Account savedAccount = new Account(1L, "John Doe", 100.0);

        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);


        AccountDto result = accountService.createAccount(accountDto);


        assertEquals(savedAccount.getId(), result.getId());
        assertEquals(savedAccount.getAccountHolderName(), result.getAccountHolderName());
        assertEquals(savedAccount.getBalance(), result.getBalance());


        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testGetAccountById() {
        Long accountId = 1L;
        Account account = new Account(accountId, "John Doe", 100.0);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        AccountDto result = accountService.getAccountById(accountId);

        assertNotNull(result);
        assertEquals(account.getId(), result.getId());
        assertEquals(account.getAccountHolderName(), result.getAccountHolderName());
        assertEquals(account.getBalance(), result.getBalance());
    }

   // @Test
   /* void testDeposit() {

        long accountId = 1L;
        double initialBalance = 100.0;
        double depositAmount = 50.0;
        Account account = new Account(accountId, "John Doe", initialBalance);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));


        AccountDto result = accountService.deposit(accountId, depositAmount);

        assertNotNull(result);
        assertEquals(accountId, result.getId());
        assertEquals("John Doe", result.getAccountHolderName());
        assertEquals(initialBalance + depositAmount, result.getBalance());


        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, times(1)).save(any(Account.class));
    }*/

   /* @Test
    void testWithdraw() {

        long accountId = 1L;
        double initialBalance = 100.0;
        double withdrawalAmount = 50.0;
        Account account = new Account(accountId, "John Doe", initialBalance);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));


        AccountDto result = accountService.withdraw(accountId, withdrawalAmount);


        assertNotNull(result);
        assertEquals(accountId, result.getId());
        assertEquals("John Doe", result.getAccountHolderName());
        assertEquals(initialBalance - withdrawalAmount, result.getBalance());


        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, times(1)).save(any(Account.class));
    }
*/
}
