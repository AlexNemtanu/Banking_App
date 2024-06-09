package proiect.aplicatiebancara.dto.mapper;

import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.model.Account;

/**
 * Utility class for mapping between Account and AccountDto objects.
 * This class provides static methods for converting Account objects to AccountDto objects
 * and vice versa.
 */
public class AccountMapper {

    /**
     * Maps an AccountDto object to an Account object.
     * @param accountDto The AccountDto object to be mapped.
     * @return The mapped Account object.
     */
    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getUser()
        );
    }

    /**
     * Maps an Account object to an AccountDto object.
     * @param account The Account object to be mapped.
     * @return The mapped AccountDto object.
     */
    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getUser()
        );
    }

}

