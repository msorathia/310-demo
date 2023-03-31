package edu.usc.csci310.project.loginsignup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

@RestController
@RequestMapping("/userController")
public class UserController {
    //@Autowired
    //public UserRepository userRepository;
    public static Hashtable<String, User> userdatabase = new Hashtable<String, User>();

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    ciphertext.append((char) ('A' + (c - 'A' + shift) % 26));
                } else {
                    ciphertext.append((char) ('a' + (c - 'a' + shift) % 26));
                }
            } else if (Character.isDigit(c))
            {
                ciphertext.append((char) ('0' + (c - '0' + shift) % 10));
            } else
            {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

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
            String en_name = encrypt(name, 5);
            String en_password = encrypt(password,5 );
            String en_email = encrypt(email, 5);

            // long emailid = Long.parseLong(en_email);
            if(userdatabase.get(en_email) != null)
            {
                String responseString = "{\"success\": \"" + "failure" + "\"," +
                        "\"passwordmatch\": \"" + "true" + "\"," +
                        "\"userexists\": \"" + "false" + "\"," +
                        "\"passwordLength\": \"" + "true" + "\"}";
                return responseString;
            }

            User user = new User(en_name, en_email, en_password);
            userdatabase.put(en_email, user);
            String responseString = "{\"success\": \"" + "true" + "\"}";
            return responseString;
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        String en_email = encrypt(email, 5);
        User user = userdatabase.get(en_email);

        if (user == null) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"loginuserexists\": \"" + "false" + "\"}";
            return responseString;
        }
        String en_password = encrypt(password, 5);

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