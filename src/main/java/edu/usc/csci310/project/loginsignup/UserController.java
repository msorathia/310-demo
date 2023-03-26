package edu.usc.csci310.project.loginsignup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/userController")
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }
        User user = new User(name, email, password);
        userRepository.save(user);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Incorrect password");
        }
        return ResponseEntity.ok("Success");
    }
}
