package edu.usc.csci310.project;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/getmoviedetails")
public class MovieDetailController {

    @GetMapping()
    public String movieDetail(@RequestParam String id) throws Exception {

        // Make API Request for most movie details
        System.out.println("request received!");
        String apiURL = "https://api.themoviedb.org/3/movie/" + id + "?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f";

        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject movieObject = new JSONObject(response.toString());
        System.out.println(movieObject);


        JSONArray genres = movieObject.getJSONArray("genres");

        String productionStudio;
        if (movieObject.getJSONArray("production_companies").length() != 0) {
            productionStudio = movieObject.getJSONArray("production_companies").getJSONObject(0).getString("name");
        }
        else {
            productionStudio = "Unknown";
        }
        String releaseDate = movieObject.getString("release_date");
        String averageRating = String.valueOf(movieObject.getDouble("vote_average"));
        String genreString;
        if (movieObject.getJSONArray("genres").length() != 0) {
            genreString = genres.getJSONObject(0).getString("name");
        }
        else {
            genreString = "Unknown";
        }
//        JSONArray cast = movieObject.getJSONObject("credits").getJSONArray("cast");
//        String actors = getActors(cast);
//        String director = getDirector(movieObject);


        // API request for credits and director

        String apiURLCredits = "https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f";

        URL urlCredits = new URL(apiURLCredits);
        HttpURLConnection connectionCredits = (HttpURLConnection) urlCredits.openConnection();
        connectionCredits.setRequestProperty("accept", "application/json");

        reader = new BufferedReader(new InputStreamReader(connectionCredits.getInputStream()));
        StringBuilder responseCredits = new StringBuilder();
        String lineCredits;

        while ((lineCredits = reader.readLine()) != null) {
            responseCredits.append(lineCredits);
        }
        reader.close();

        JSONObject creditsObject = new JSONObject(responseCredits.toString());
        System.out.println(movieObject);

        // Make a list of actor names and check to find the director:

        String characterNames = "";
        String directorName = "";
        JSONArray castArray = creditsObject.getJSONArray("cast");
        JSONArray crewArray = creditsObject.getJSONArray("crew");
        for (int i = 0; i < castArray.length(); i++) {
            JSONObject castObject = castArray.getJSONObject(i);
            characterNames += (castObject.getString("name") + ",");
        }
        for (int i = 0; i < crewArray.length(); i++) {
            JSONObject crewObject = crewArray.getJSONObject(i);
            if ((crewObject.getString("job").toLowerCase().equals("director"))) {
                directorName = crewObject.getString("name");
                break;
            }
        }

        if (characterNames.length() != 0) {
            characterNames = characterNames.substring(0, characterNames.length() - 1);
        }

        System.out.println(characterNames);
        System.out.println("Director is " + directorName);

        if (directorName == "") {
            directorName = "Unknown";
        }





        // construct a response string and send it
        String responseString = "{\"genre\": \"" + genreString + "\", \n" +
                                    "\"studio\": \"" + productionStudio + "\", \n" +
                                    "\"releaseDate\": \"" + releaseDate + "\", \n" +
                                    "\"director\": \"" + directorName + "\", \n" +
                                    "\"actors\": \"" + characterNames + "\", \n" +
                                    "\"rating\": \"" + averageRating + "\"}";
        System.out.println("Sending movie detial line");
        System.out.println(responseString);
        return responseString;
    }
}
