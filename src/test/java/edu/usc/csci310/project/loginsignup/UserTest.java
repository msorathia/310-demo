package edu.usc.csci310.project.loginsignup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void setId() {
        User user = new User("name", "email", "123");
        user.setId((12L));
        assertEquals(12L, user.getId());
    }

    @Test
    void getName() {
        User user = new User("name", "email", "123");
        user.setName("newname");
        assertEquals("newname", user.getName());
    }

    @Test
    void setName() {
        User user = new User("name", "email", "123");
        user.setName("newname2");
        assertEquals("newname2", user.getName());
    }
    @Test
    void getEmail() {
        User user = new User("name", "email", "123");
        user.setEmail("newemail");
        assertEquals("newemail", user.getEmail());
    }

    @Test
    void setEmail() {
        User user = new User("name", "email", "123");
        user.setEmail("newemail2");
        assertEquals("newemail2", user.getEmail());
    }

    @Test
    void getPassword() {
        User user = new User("name", "email", "123");
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User("name", "email", "123");
        user.setPassword("newpassword2");
        assertEquals("newpassword2", user.getPassword());
    }
}