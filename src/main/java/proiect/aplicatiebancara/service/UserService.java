package proiect.aplicatiebancara.service;

import proiect.aplicatiebancara.dto.UserDto;
import proiect.aplicatiebancara.model.User;

public interface UserService {
    User save(UserDto userDto);
    User findByUsername(String username);
   /* User updateAccount(UserDto userDto, String fullname, String password);*/
    void deleteUser(String username);
}
