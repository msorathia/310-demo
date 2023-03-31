package edu.usc.csci310.project;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

// 973534
class PictureMontageControllerTest {

    PictureMontageController pmc = new PictureMontageController();
    @Test
    void pictureMontage() throws Exception {
        pmc.setMovies(new ArrayList<>(Arrays.asList("646385", "493922", "49018", "27205",
                "11", "181808", "1895", "24428", "299536", "1885")));
        String returnedResponse = pmc.pictureMontage("user");

        ArrayList<String> images = new ArrayList<>(Arrays.asList(returnedResponse));
        assertNotNull(returnedResponse);
        assert(images.size() == 10);
    }

    @Test
    void lessThan10Movies() throws Exception {
        ArrayList<String> movies = new ArrayList<>(Arrays.asList("646385"));
        pmc.setMovies(movies);
        String returnedResponse = pmc.pictureMontage("user");
        ArrayList<String> images = new ArrayList<>(Arrays.asList(returnedResponse));

        assert (movies.get(0) == pmc.getMovies().get(0));
        assert(images.size() == 10);
        // format "user id: img_link"
        for (String image: images) {
            assert(image.contains("646385"));
        }

    }
}