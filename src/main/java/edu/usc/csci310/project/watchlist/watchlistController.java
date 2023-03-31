package edu.usc.csci310.project.watchlist;

import edu.usc.csci310.project.loginsignup.User;
import edu.usc.csci310.project.loginsignup.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

@RestController
@RequestMapping("/watchlistController")
public class watchlistController {
    public Hashtable<String, User> userdatabase = new Hashtable<String, User>();

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

    public static String decrypt(String ciphertext) {
        return encrypt(ciphertext, 21);
    }

    @PostMapping("/createwatchlist")
    public String createwatchlist(@RequestParam String watchlistname, @RequestParam String email) {
        //encrypting
        UserController userController = new UserController();
        userController.signUp("john", "dummywatchlist@usc.edu", "1234", "1234");
        String en_email = encrypt(email, 5);

        User user = userdatabase.get(en_email);
        if (user != null) {
            String en_name = encrypt(watchlistname, 5);

            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            ArrayList<String> specificlist = alllists.get(en_name);
            if (specificlist == null) {
                user.createnewList(en_name);
                String responseString = "{\"success\": \"" + "true" + "\"," +
                        "\"failure\": \"" + "false" + "\"}";
                return responseString;
            } else {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                return responseString;
            }
        }
        String responseString = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        return responseString;
    }

    @PostMapping("/retrievelist")
    public String retrieveList(@RequestParam String email, @RequestParam String watchlistname)
    {
        UserController userController = new UserController();
        userController.signUp("john", "dummywatchlist@usc.edu", "1234", "1234");
        //userController.userRepository = userRepository;
        String en_email = encrypt(email, 5);
        User user = userdatabase.get(en_email);
        if(user != null)
        {
            String en_name = encrypt(watchlistname, 5);

            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            ArrayList<String> specificlist = alllists.get(en_name);
            if(specificlist == null)
            {
                return "";
            }
            else
            {
                String id = "";
                String responseString = "[ ";
                for(int i = 0; i < specificlist.size(); i++)
                {
                    String encrypted_id = specificlist.get(i);
                    responseString = responseString + decrypt(encrypted_id) + ", ";
                }
                responseString = responseString + "]";
                return responseString;
            }

        }
        return "";
    }


    @PostMapping("/addtolist")
    public String addtolist(@RequestParam String email, @RequestParam String watchlistname, @RequestParam String id)
    {
        UserController userController = new UserController();
        userController.signUp("john", "dummywatchlist@usc.edu", "123456789", "123456789");
        //userController.userRepository = userRepository;
        String en_email = encrypt(email, 5);
        System.out.println(email + watchlistname + id);

        User user = userdatabase.get(en_email);
        if(user != null)
        {
            System.out.println("Entered");
            String en_name = encrypt(watchlistname, 5);
            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            ArrayList<String> specificlist = alllists.get(en_name);
            if(specificlist == null)
            {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                System.out.println("Null");
                return responseString;
            }
            else {
                user.addtowatchlist(id, encrypt(watchlistname, 5));
                String responseString = "{\"success\": \"" + "true" + "\"," +
                        "\"failure\": \"" + "false" + "\"}";
                System.out.println("Not Null");
                return responseString;
            }
        }
        String responseString = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        return responseString;
    }
}