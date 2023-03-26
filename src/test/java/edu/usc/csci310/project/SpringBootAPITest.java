package edu.usc.csci310.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SpringBootAPITest {

    @Test
    void main() {
        
        String[] args = new String[0];
        SpringBootAPI.main(args);
    }

    @Test
    void redirect() {
        SpringBootAPI apiObject = new SpringBootAPI();
        String response = apiObject.redirect();
        assertTrue(response.contains("forward:/"));
    }
}