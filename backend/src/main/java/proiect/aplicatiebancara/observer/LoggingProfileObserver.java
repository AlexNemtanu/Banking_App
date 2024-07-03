package proiect.aplicatiebancara.observer;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import proiect.aplicatiebancara.model.Account;

import org.slf4j.Logger;

/**
 * Observer implementation that logs changes to an account.
 * This class logs messages when notified of changes to an account.
 */
@Component
public class LoggingProfileObserver implements AccountObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingProfileObserver.class);

    /**
     * Method called when an observed account is updated.
     * Logs a message indicating that a new account has been made.
     * @param account The updated account.
     */
    @Override
    public void update(Account account){
        LOGGER.info("A new account has been made");
    }
}

