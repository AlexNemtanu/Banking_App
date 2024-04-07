package proiect.aplicatiebancara.repository;

import proiect.aplicatiebancara.model.User;

public interface UserRepository {
    User findById(long id);
    void save(User user);

}
