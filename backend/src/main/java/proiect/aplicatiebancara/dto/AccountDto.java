package proiect.aplicatiebancara.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import proiect.aplicatiebancara.model.User;

/**
 * Data Transfer Object (DTO) class representing an account.
 * This class encapsulates account data such as ID, account holder name, and balance.
 */
@Data
@AllArgsConstructor
public class AccountDto {
    /**
     * The ID of the account.
     */
    private Long id;

    /**
     * The name of the account holder.
     */
    private String accountHolderName;

    /**
     * The balance of the account.
     */
    private double balance;
    private User user;

    public AccountDto() {
    }
}
