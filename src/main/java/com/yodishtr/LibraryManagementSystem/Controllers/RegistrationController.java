package com.yodishtr.LibraryManagementSystem.Controllers;

import com.yodishtr.LibraryManagementSystem.Entities.User;
import com.yodishtr.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public RegistrationController(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                           Model model){
        if (username == null || username.trim().isEmpty()){
            model.addAttribute("error", "Username cannot be empty");
            return "register";
        }
        if (password == null || password.length() < 8){
            model.addAttribute("error", "Password must be at least 8 characters");
            return "register";
        }
        Optional<User> currUserRequested = userRepository.findByUsername(username);
        if (currUserRequested.isPresent()){
            model.addAttribute("error", "Username already taken");
            return "register";
        } else {
            String hashedPassword = bCryptPasswordEncoder.encode(password);
            User newUserInDb = new User(username, hashedPassword, email, User.ROLE.USER);
            try{
                userRepository.save(newUserInDb);
                return "redirect:/login";
            } catch (Exception e){
                model.addAttribute("error", "User could not be saved");
                return "register";
            }
        }
    }
}
