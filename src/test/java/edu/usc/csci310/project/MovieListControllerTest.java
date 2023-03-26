package edu.usc.csci310.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieListControllerTest {

    MovieListController mlc = new MovieListController();
    @Test
    void movieList() throws Exception {

        String returnedResponse = mlc.movieList("fast and furious", "keyword", "1");

        assertNotNull(returnedResponse);
        assertTrue(returnedResponse.contains("overview"));
        assertTrue(returnedResponse.contains("title"));
        assertTrue(returnedResponse.contains("Tokyo Drift"));
    }
}

