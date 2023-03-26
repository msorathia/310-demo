package edu.usc.csci310.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/getmovies")
public class MovieListController {

    @GetMapping()
    public String movieList(@RequestParam String query, @RequestParam String type, @RequestParam String page) throws Exception {

        // Make API Request
        System.out.println("request received!");
        String apiURL = "https://api.themoviedb.org/3/search/movie?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f" +
                "&query=" + URLEncoder.encode(query, "UTF-8") + "&page=" + page +
                "&include_adult=false&" + type + "_search=true";

        System.out.println("HERE!");
        System.out.println(apiURL);
        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        String responseString = response.toString();

        return responseString;
    }
}
