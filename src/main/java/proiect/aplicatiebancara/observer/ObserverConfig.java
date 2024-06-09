package proiect.aplicatiebancara.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import proiect.aplicatiebancara.service.impl.AccountServiceImpl;

/**
 * Configuration class for configuring observers.
 * This class wires up observer(s) to the corresponding service(s) upon application startup.
 */
@Configuration
public class ObserverConfig {

    /**
     * Configures observers for account-related operations.
     * Adds the logging profile observer to the account service.
     * @param accountService The account service to observe.
     * @param loggingProfileObserver The logging profile observer.
     */
    @Autowired
    public void configureObserver(AccountServiceImpl accountService, LoggingProfileObserver loggingProfileObserver){
        accountService.addObserver(loggingProfileObserver);
    }
}

