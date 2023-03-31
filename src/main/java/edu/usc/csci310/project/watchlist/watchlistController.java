package edu.usc.csci310.project.watchlist;

import edu.usc.csci310.project.loginsignup.User;
import edu.usc.csci310.project.loginsignup.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/watchlistController")
public class watchlistController {
    @Autowired
    public UserRepository userRepository;

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

    @PostMapping("/createlist")
    public String createList(@RequestParam String email, @RequestParam String watchlistname, @RequestParam String id)
    {
        String en_email = encrypt(email, 5);
        User user = userRepository.findByEmail(en_email);
        if(user != null) {
            String en_name = encrypt(watchlistname, 5);
            ArrayList<String> specificlist = user.getWatchList(en_name);
            if (specificlist == null) {
                user.createnewList(en_name);
                user.addtowatchlist(encrypt(id, 5), en_name);
                String responseString = "{\"success\": \"" + "true" + "\"," +
                        "\"failure\": \"" + "false" + "\"}";
                return responseString;
            } else {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"alreadyexists\": \"" + "true" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                return responseString;
            }
        }
        String responseString = "{\"success\": \"" + "false" + "\"," +
                "\"alreadyexists\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        return responseString;
    }


    @PostMapping("/addtolist")
    public String addtolist(@RequestParam String email, @RequestParam String watchlistname, @RequestParam String id)
    {
        String en_email = encrypt(email, 5);
        User user = userRepository.findByEmail(en_email);
        if(user != null)
        {
            String en_name = encrypt(watchlistname, 5);
            HashMap<String, ArrayList<String>> alllists = user.getAllWatchlists();
            if(alllists == null)
            {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                return responseString;
            }
            ArrayList<String> specificlist = alllists.get(en_name);
            if(specificlist == null)
            {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                return responseString;
            }
            else {
                boolean answer = user.addtowatchlist(en_name, encrypt(id, 5));
                if(answer == true) {
                    String responseString = "{\"success\": \"" + "true" + "\"," +
                            "\"failure\": \"" + "false" + "\"}";
                    return responseString;
                }
            }
        }
        String responseString = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        return responseString;
    }

    @PostMapping("/showlists")
    public String showallLists(@RequestParam String email)
    {
        String en_email = encrypt(email, 5);
        User user = userRepository.findByEmail(en_email);
        if(user != null)
        {
            String alllistnames = "[ ";
            HashMap<String, ArrayList<String>> allnames = user.getAllWatchlists();
            if(allnames == null)
                return "[]";
            if(allnames.isEmpty())
                return "[]";

            for(String key: allnames.keySet())
            {
                alllistnames = alllistnames + decrypt(key) + ", ";
            }

            alllistnames = alllistnames.substring(0,alllistnames.length()-2) + "]";
            return alllistnames;
        }
        return "[]";
    }
}