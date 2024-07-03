package proiect.aplicatiebancara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.aplicatiebancara.model.Account;

/**
 * Repository interface for managing Account entities.
 * This interface provides methods for CRUD operations on Account entities.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

