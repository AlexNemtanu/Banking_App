package proiect.aplicatiebancara.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.service.AccountService;

import java.util.List;
import java.util.Map;

/**
 * Controller class for handling account-related HTTP requests.
 * This class defines endpoints for performing operations such as adding, retrieving,
 * depositing, withdrawing, transferring, and deleting accounts.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Constructs a new AccountController with the specified AccountService.
     * @param accountService The AccountService to be used for account-related operations.
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint for adding a new account.
     * @param accountDto The AccountDto object containing account details to be added.
     * @return ResponseEntity containing the created AccountDto and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving an account by its ID.
     * @param id The ID of the account to retrieve.
     * @return ResponseEntity containing the retrieved AccountDto and HTTP status code.
     */
    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Endpoint for depositing funds into an account.
     * @param id The ID of the account to deposit funds into.
     * @param request A Map containing the amount to deposit.
     * @return ResponseEntity containing the updated AccountDto and HTTP status code.
     */
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Endpoint for withdrawing funds from an account.
     * @param id The ID of the account to withdraw funds from.
     * @param request A Map containing the amount to withdraw.
     * @return ResponseEntity containing the updated AccountDto and HTTP status code.
     */
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Endpoint for retrieving all accounts.
     * @return ResponseEntity containing a list of AccountDto objects and HTTP status code.
     */
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    /**
     * Endpoint for deleting an account by its ID.
     * @param id The ID of the account to delete.
     * @return ResponseEntity containing a success message and HTTP status code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account was deleted");
    }

    /**
     * Endpoint for transferring money from one account to another.
     * @param fromAccountId The ID of the account to transfer money from.
     * @param toAccountId The ID of the account to transfer money to.
     * @param request A Map containing the amount to transfer.
     * @return ResponseEntity containing a success message and HTTP status code.
     */
    @PutMapping("/{fromAccountId}/transfer/{toAccountId}")
    public ResponseEntity<String> transferMoney(@PathVariable Long fromAccountId, @PathVariable Long toAccountId,
                                                @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        accountService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok("Money transferred successfully");
    }

}

