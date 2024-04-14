package proiect.aplicatiebancara.observer;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import proiect.aplicatiebancara.model.Account;

import org.slf4j.Logger;

@Component
public class LoggingProfileObserver implements AccountObserver{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingProfileObserver.class);

    @Override
    public void update(Account account){
        LOGGER.info("A new account has been made");
    }
}
