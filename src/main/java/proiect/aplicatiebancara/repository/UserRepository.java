package proiect.aplicatiebancara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.aplicatiebancara.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
