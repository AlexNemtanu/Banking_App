package proiect.aplicatiebancara.service.impl;

import org.springframework.stereotype.Service;
import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.dto.mapper.AccountMapper;
import proiect.aplicatiebancara.model.Account;
import proiect.aplicatiebancara.observer.AccountObserver;
import proiect.aplicatiebancara.repository.AccountRepository;
import proiect.aplicatiebancara.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the AccountService interface.
 * This service class provides methods for managing accounts and notifies observers of account changes.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final List<AccountObserver> observers = new ArrayList<>();


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    /**
     * Creates a new account with the provided account details.
     * @param accountDto The account details.
     * @return The created account DTO.
     */
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        notifyObservers(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    /**
     * Retrieves an account by its ID.
     * @param id The ID of the account to retrieve.
     * @return The account DTO.
     * @throws RuntimeException If the account does not exist.
     */
    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        if (account == null) {
            throw new RuntimeException("Account is null");
        }
        return AccountMapper.mapToAccountDto(account);
    }

    /**
     * Deposits money into the specified account.
     * @param id The ID of the account.
     * @param amount The amount to deposit.
     * @return The updated account DTO.
     * @throws RuntimeException If the account does not exist or if the deposit amount is invalid.
     */
    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        if (account == null) {
            throw new RuntimeException("Account is null");
        }

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    /**
     * Withdraws money from the specified account.
     * @param id The ID of the account.
     * @param amount The amount to withdraw.
     * @return The updated account DTO.
     * @throws RuntimeException If the account does not exist, if the withdrawal amount is invalid,
     *                          or if there are insufficient funds in the account.
     */
    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        if (account == null) {
            throw new RuntimeException("Account is null");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    /**
     * Retrieves all accounts.
     * @return A list of account DTOs.
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());

    }

    /**
     * Deletes an account by its ID.
     * @param id The ID of the account to delete.
     * @throws RuntimeException If the account does not exist.
     */
    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);
    }

    /**
     * Transfers money from one account to another.
     * @param fromId The ID of the account to transfer money from.
     * @param toId The ID of the account to transfer money to.
     * @param amount The amount to transfer.
     * @throws RuntimeException If either account does not exist or if the transfer amount is invalid.
     */
    @Override
    public void transfer(Long fromId, Long toId, double amount) {
        Account fromAccount = accountRepository.
                findById(fromId).
                orElseThrow(() -> new RuntimeException("The account from which the transfer is made does not exist\n"));

        Account toAccount = accountRepository
                .findById(toId)
                .orElseThrow(() -> new RuntimeException("The account in which the transfer will be made does not exist\n"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in the 'from' account");
        }

        double fromAccountBalance = fromAccount.getBalance() - amount;
        fromAccount.setBalance(fromAccountBalance);
        accountRepository.save(fromAccount);

        double toAccountBalance = toAccount.getBalance() + amount;
        toAccount.setBalance(toAccountBalance);
        accountRepository.save(toAccount);

    }
    @Override
    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Account account) {
        for (AccountObserver observer : observers) {
            observer.update(account);
        }
    }








}
