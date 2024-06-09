package proiect.aplicatiebancara.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import proiect.aplicatiebancara.dto.UserDto;
import proiect.aplicatiebancara.model.User;
import proiect.aplicatiebancara.service.UserService;

@RestController
@RequestMapping("/auth/api")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUserRest(@RequestBody UserDto userDto) {
        try {
            userService.save(userDto);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Username already exists!")) {
                return new ResponseEntity<>("Username already exists!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUserRest(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
}
