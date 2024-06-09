package proiect.aplicatiebancara.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proiect.aplicatiebancara.dto.UserDto;
import proiect.aplicatiebancara.model.User;
import proiect.aplicatiebancara.repository.UserRepository;
import proiect.aplicatiebancara.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new RuntimeException("Username already exists!");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword( passwordEncoder.encode(userDto.getPassword()));
        //user.setPassword(userDto.getPassword());
        user.setFullname(userDto.getFullname());
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /*public void updateAccount(User user, String fullname, String password) {
        user.setFullname(fullname);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }*/
    @Override
    public void deleteUser(String username) {
        User user = findByUsername(username);
        userRepository.delete(user);

    }

}
