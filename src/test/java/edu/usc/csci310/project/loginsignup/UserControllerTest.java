package edu.usc.csci310.project.loginsignup;


import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static edu.usc.csci310.project.loginsignup.UserController.encrypt;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
public class UserControllerTest {

    @Test
    public void signUpFirst() {         //passwords do not match
        UserController user = new UserController();
        String answer = user.signUp("monil", "monil@usc.edu", "123", "1234");

        String expected = "{\"success\": \"" + "failure" + "\"," +
                "\"passwordmatch\": \"" + "false" + "\"}";
        assertEquals(expected, answer);
    }

    @Test
    public void signUpSecond() {                //password length incorrect
        UserController user = new UserController();
        String answer = user.signUp("monil", "monil@usc.edu", "1234", "1234");

        String expected = "{\"success\": \"" + "failure" + "\"," +
                "\"passwordmatch\": \"" + "true" + "\"," +
                "\"passwordLength\": \"" + "false" + "\"}";
        assertEquals(expected, answer);
    }

    @Test
    public void signUpThird() {         //user already exists
        UserController user = new UserController();
        Hashtable<String, User> mockrepo = mock(Hashtable.class);
        user.userdatabase = mockrepo;
        User newuser = new User("temp", "temp", "temp");

        when(mockrepo.get(anyString())).thenReturn(newuser);

        String answer = user.signUp("monil", "monil@usc.edu", "12345678", "12345678");

        String expected = "{\"success\": \"" + "failure" + "\"," +
                "\"passwordmatch\": \"" + "true" + "\"," +
                "\"userexists\": \"" + "false" + "\"," +
                "\"passwordLength\": \"" + "true" + "\"}";
        assertEquals(expected, answer);
    }

    @Test
    public void signUpFourth() {         //successful sign up
        UserController user = new UserController();
        Hashtable<String, User> mockrepo = mock(Hashtable.class);
        user.userdatabase = mockrepo;
        User newuser = new User("temp", "temp", "temp");

        when(mockrepo.get(anyString())).thenReturn(null);
        when(mockrepo.put(anyString(), isA(User.class))).thenReturn(newuser);
        String answer = user.signUp("monil", "monil@usc.edu", "12345678", "12345678");

        String expected = "{\"success\": \"" + "true" + "\"}";
        assertEquals(expected, answer);
    }
    @Test
    public void loginFirst() {
        UserController user = new UserController();
        Hashtable<String, User> mockrepo = mock(Hashtable.class);
        user.userdatabase = mockrepo;

        String answer = user.login("temp", "temp");
        when(mockrepo.get(anyString())).thenReturn(null);

        String expected = "{\"success\": \"" + "failure" + "\"," +
                "\"loginuserexists\": \"" + "false" + "\"}";

        assertEquals(expected, answer);
    }


    @Test
    public void loginSecond() {
        UserController user = new UserController();
        Hashtable<String, User> mockrepo = mock(Hashtable.class);
        user.userdatabase = mockrepo;

        User mockuser = mock(User.class);
        when(mockrepo.get(anyString())).thenReturn(mockuser);


        when(mockuser.getPassword()).thenReturn("");
        String answer = user.login("temp", "temp");
        String expected = "{\"success\": \"" + "failure" + "\"," +
                "\"loginpasswordmatch\":\"" + "false" + "\"," +
                "\"loginuserexists\": \"" + "true" + "\"}";
        assertEquals(expected, answer);
    }
    @Test
    public void loginThird() {
        UserController user = new UserController();
        Hashtable<String, User> mockrepo = mock(Hashtable.class);
        user.userdatabase = mockrepo;

        User mockuser = mock(User.class);
        mockuser.setEmail("temp");
        mockuser.setPassword("temp");
        mockuser.setName("temp");
        when(mockrepo.get(anyString())).thenReturn(mockuser);

        String en_password = "";
        String password = "temp";
        en_password = encrypt(password, 5);
        when(mockuser.getPassword()).thenReturn(en_password);
        String expected = "{\"success\": \"" + "true" + "\"}";
        String answer = user.login("temp", "temp");

        assertEquals(expected, answer);
    }
}