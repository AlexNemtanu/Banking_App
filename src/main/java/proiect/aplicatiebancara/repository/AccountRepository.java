package proiect.aplicatiebancara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.aplicatiebancara.model.Account;

/**
 * Repository interface for managing Account entities.
 * This interface provides methods for CRUD operations on Account entities.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}

