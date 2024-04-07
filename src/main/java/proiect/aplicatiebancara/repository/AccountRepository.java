package proiect.aplicatiebancara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.aplicatiebancara.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

