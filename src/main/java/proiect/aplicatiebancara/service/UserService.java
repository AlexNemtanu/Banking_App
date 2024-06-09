package proiect.aplicatiebancara.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import proiect.aplicatiebancara.model.User;
import proiect.aplicatiebancara.dto.UserDto;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);
}
