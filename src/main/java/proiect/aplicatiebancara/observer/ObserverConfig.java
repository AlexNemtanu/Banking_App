package proiect.aplicatiebancara.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import proiect.aplicatiebancara.service.impl.AccountServiceImpl;

@Configuration
public class ObserverConfig {

    @Autowired
    public void configureObserver(AccountServiceImpl accountService, LoggingProfileObserver loggingProfileObserver){
        accountService.addObserver(loggingProfileObserver);
    }
}
