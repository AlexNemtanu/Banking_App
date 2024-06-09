package proiect.aplicatiebancara.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import proiect.aplicatiebancara.dto.AccountDto;
import proiect.aplicatiebancara.dto.UserDto;
import proiect.aplicatiebancara.model.Account;
import proiect.aplicatiebancara.model.User;
import proiect.aplicatiebancara.service.AccountService;
import proiect.aplicatiebancara.service.UserService;

import java.security.Principal;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Controller
public class AuthController {

    private final UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    private final AccountService accountService;

    public AuthController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("userdetail", user);
        model.addAttribute("accounts", user.getUsername());
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }


    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model){
        User user = userService.findByUsername(userDto.getUsername());
        if(user != null){
            model.addAttribute("existinguser" ,user);
            return "register";
        }
        userService.save(userDto);
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountHolderName(userDto.getFullname());
        accountDto.setBalance(0.0);
        accountService.createAccount(accountDto);
        return "redirect:/register?success";
    }
}
