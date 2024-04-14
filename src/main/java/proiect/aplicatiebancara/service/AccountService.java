package proiect.aplicatiebancara.service;

import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.model.Account;
import proiect.aplicatiebancara.observer.AccountObserver;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    void transfer(Long fromId, Long toId, double amount);

    void addObserver(AccountObserver observer);

    void notifyObservers(Account account);
}
