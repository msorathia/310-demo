package edu.usc.csci310.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 973534
class MovieDetailControllerTest {

    MovieDetailController mdc = new MovieDetailController();
    @Test
    void movieDetail() throws Exception {

        String returnedResponse = mdc.movieDetail("550");

        assertNotNull(returnedResponse);
        assertTrue(returnedResponse.contains("releaseDate"));
        assertTrue(returnedResponse.contains("genre"));
        assertTrue(returnedResponse.contains("studio"));
        assertTrue(returnedResponse.contains("director"));
        assertTrue(returnedResponse.contains("actors"));
        assertTrue(returnedResponse.contains("rating"));

        returnedResponse = mdc.movieDetail("973534");
        assertNotNull(returnedResponse);
        assertTrue(returnedResponse.contains("releaseDate"));
        assertTrue(returnedResponse.contains("genre"));
        assertTrue(returnedResponse.contains("studio"));
        assertTrue(returnedResponse.contains("director"));
        assertTrue(returnedResponse.contains("actors"));
        assertTrue(returnedResponse.contains("rating"));

    }
    
    @Test
    void movieDetailMissingDetails() throws Exception {

        String returnedResponse = mdc.movieDetail("973534");

        assertNotNull(returnedResponse);
        assertTrue(returnedResponse.contains("releaseDate"));
        assertTrue(returnedResponse.contains("genre"));
        assertTrue(returnedResponse.contains("studio"));
        assertTrue(returnedResponse.contains("director"));
        assertTrue(returnedResponse.contains("actors"));
        assertTrue(returnedResponse.contains("rating"));

        returnedResponse = mdc.movieDetail("973534");
        assertNotNull(returnedResponse);
        assertTrue(returnedResponse.contains("releaseDate"));
        assertTrue(returnedResponse.contains("genre"));
        assertTrue(returnedResponse.contains("studio"));
        assertTrue(returnedResponse.contains("director"));
        assertTrue(returnedResponse.contains("actors"));
        assertTrue(returnedResponse.contains("rating"));
        assertTrue(returnedResponse.contains("rating"));

    }


}