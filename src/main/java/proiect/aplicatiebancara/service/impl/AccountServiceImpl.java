package proiect.aplicatiebancara.service.impl;

import org.springframework.stereotype.Service;
import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.dto.mapper.AccountMapper;
import proiect.aplicatiebancara.model.Account;
import proiect.aplicatiebancara.repository.AccountRepository;
import proiect.aplicatiebancara.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);
    }

    @Override
    public void transfer(Long fromId, Long toId, double amount) {
        Account fromAccount = accountRepository.
                findById(fromId).
                orElseThrow(()-> new RuntimeException("The account from which the transfer is made does not exist\n"));

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
}
