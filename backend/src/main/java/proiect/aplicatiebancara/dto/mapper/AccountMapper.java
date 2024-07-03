package proiect.aplicatiebancara.dto.mapper;

import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.model.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountHolderName(accountDto.getAccountHolderName()); // Folosește setAccountHolderName
        account.setBalance(accountDto.getBalance());
        account.setUser(accountDto.getUser());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountHolderName(account.getAccountHolderName()); // Folosește getAccountHolderName
        accountDto.setBalance(account.getBalance());
        accountDto.setUser(account.getUser());
        return accountDto;
    }
}
