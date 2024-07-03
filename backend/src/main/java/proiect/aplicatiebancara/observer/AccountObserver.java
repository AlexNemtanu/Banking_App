package proiect.aplicatiebancara.observer;

import proiect.aplicatiebancara.model.Account;
/**
 * Interface for observing changes to an account.
 * Implementations of this interface can observe changes to an account and perform actions accordingly.
 */
public interface AccountObserver {
    /**
     * Method called when an observed account is updated.
     * @param account The updated account.
     */
    void update(Account account);
}
