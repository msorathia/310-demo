package edu.usc.csci310.project;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/getpicturemontage")
public class PictureMontageController {
    ArrayList<String> userMovies;
    public PictureMontageController() {
        setUsersMovies();
    }

    @GetMapping()
    public String pictureMontage(@RequestParam String user) throws Exception {
        // make api call for montage
        ArrayList<String> images = new ArrayList<String>();

        System.out.println("request received from " + user);
        Map<String, ArrayList<String>> moviePostersMap = new HashMap<String, ArrayList<String>>();

        String base = "https://image.tmdb.org/t/p";
        String width = "/w500";
        for (String id : userMovies) {
            ArrayList<String> posterFilePaths = new ArrayList<>();
            String apiURL = "https://api.themoviedb.org/3/movie/" + id +
                    "/images?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f&include_image_language=en";
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
            JSONObject imagesObject = new JSONObject(response.toString());
            JSONArray postersArray = imagesObject.getJSONArray("posters");


            for (int i = 0; i < postersArray.length(); i++) {
                JSONObject posterObject = postersArray.getJSONObject(i);
                String filePath = posterObject.getString("file_path");
                posterFilePaths.add(filePath);
            }
            moviePostersMap.put(id, posterFilePaths);
        }
        while (images.size() < 10) {
            for (String id : userMovies) {
                List<String> posters = moviePostersMap.get(id);
                String imageEntry = "\"" + id + "\"" + ": " + "\"" + base + width + posters.get(0) + "\"";
                System.out.println("Inserting " + imageEntry);
                images.add(imageEntry);
                posters.remove(0);
            }
        }
        String res = "{";
        for (int i = 0; i < 10; i++) {
            res += images.get(i);
            if (i != 9) {
                res += ",";
            }
        }
        res += "}";
        System.out.println(res);
        return res;
        
    }
    public void setMovies(ArrayList<String> movies) {
        userMovies = movies;
    }
    public ArrayList<String> getMovies() {
        return userMovies;
    }
    public void setUsersMovies() {
        // to do. do a database search for movie ids in movie list

        // temp hard coded

        setMovies(new ArrayList<>(Arrays.asList("646385", "493922", "49018", "27205",
                    "11", "181808", "1895", "24428", "299536", "1885")));
    }
}