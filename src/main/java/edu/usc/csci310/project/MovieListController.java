package edu.usc.csci310.project;

import edu.usc.csci310.project.demo.api.requests.PingPongRequest;
import edu.usc.csci310.project.demo.api.responses.PingPongResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.Instant;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/getmovies")
public class MovieListController {

    @GetMapping()
    public String movieList(@RequestParam String query, @RequestParam String type, @RequestParam String page) throws Exception {

        if (type.equals("title")) {
            // Make movie search API request
            String apiURL = "https://api.themoviedb.org/3/search/movie?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f" +
                    "&query=" + URLEncoder.encode(query, "UTF-8") + "&page=" + page;

            // Send HTTP GET request to API and read response
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
        } else if (type.equals("actor")) {
            // Make API Request for person search
            String apiURLPersonSearch = "https://api.themoviedb.org/3/search/person?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f&query=" + URLEncoder.encode(query, "UTF-8");

            URL url = new URL(apiURLPersonSearch);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String responseString = response.toString();

            // Parse the JSON response to get the person ID for the first person in the results
            JSONObject jsonObject = new JSONObject(responseString);
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            JSONObject person = resultsArray.getJSONObject(0);
            int personId = person.getInt("id");

            // Make API Request for movie credits for the person
            String apiURLMovieList = "https://api.themoviedb.org/3/discover/movie?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f&language=en-US&with_people=" + personId + "&page=" + page;

            url = new URL(apiURLMovieList);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            responseString = response.toString();

            return responseString;

        } else if (type.equals("keyword")) {

            String apiURLKeywordSearch = "https://api.themoviedb.org/3/search/keyword?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f&query=" + URLEncoder.encode(query, "UTF-8");

            URL url = new URL(apiURLKeywordSearch);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String responseString = response.toString();

            // Parse the JSON response to get the keyword ID for the first keyword in the results
            JSONObject jsonObject = new JSONObject(responseString);
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            JSONObject keyword = resultsArray.getJSONObject(0);
            int keywordId = keyword.getInt("id");

            // Make API Request for list of movies with the keyword
            String apiURLMovieList = "https://api.themoviedb.org/3/discover/movie?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f&language=en-US&with_keywords=" + keywordId + "&page=" + page;

            url = new URL(apiURLMovieList);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            responseString = response.toString();

            return responseString;
        }
        //If search type is not valid
        return "Invalid search type";
    }
}