package edu.usc.csci310.project.loginsignup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userController")
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @PostMapping("/signup")
    public String signUp(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                                        "\"passwordmatch\": \"" + "false" + "\"}";
            return responseString;
        }
        if(password.length() < 8) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"passwordmatch\": \"" + "true" + "\"," +
                    "\"passwordLength\": \"" + "false" + "\"}";
            return responseString;
        }
        else {
            //encrypting
            String en_name = "";
            String en_password = "";
            String en_email = "";
            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);
                int ascii_val = (ch + 5) % 100;
                en_name = en_name + Integer.toString(ascii_val);
            }

            for (int i = 0; i < email.length(); i++) {
                char ch = email.charAt(i);
                int ascii_val = (ch + 5) % 100;
                en_email = en_email + Integer.toString(ascii_val);
            }
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                int ascii_val = (ch + 5) % 100;
                en_password = en_password + Integer.toString(ascii_val);
            }
            // long emailid = Long.parseLong(en_email);
            if(userRepository.findByEmail(en_email) != null)
            {
                String responseString = "{\"success\": \"" + "failure" + "\"," +
                        "\"passwordmatch\": \"" + "true" + "\"," +
                        "\"userexists\": \"" + "false" + "\"," +
                        "\"passwordLength\": \"" + "true" + "\"}";
                return responseString;
            }

            User user = new User(en_name, en_email, en_password);
            userRepository.save(user);
            String responseString = "{\"success\": \"" + "true" + "\"}";
            return responseString;
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        String en_email = "";
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            int ascii_val = (ch + 5) % 100;
            en_email = en_email + Integer.toString(ascii_val);
        }
        User user = userRepository.findByEmail(en_email);

        if (user == null) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"loginuserexists\": \"" + "false" + "\"}";
            return responseString;
        }
        String en_password = "";
        for(int i = 0; i < password.length(); i++)
        {
            char ch = password.charAt(i);
            int ascii_val = (ch + 5)%100;
            en_password = en_password + Integer.toString(ascii_val);
        }
        if (!user.getPassword().equals(en_password)) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"loginpasswordmatch\":\"" + "false" + "\"," +
                    "\"loginuserexists\": \"" + "true" + "\"}";
            return responseString;
        }
        String responseString = "{\"success\": \"" + "true" + "\"}";
        return responseString;
    }
}
