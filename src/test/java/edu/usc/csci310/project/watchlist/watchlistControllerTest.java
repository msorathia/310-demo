package edu.usc.csci310.project.watchlist;

import edu.usc.csci310.project.loginsignup.User;
import edu.usc.csci310.project.loginsignup.UserRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static edu.usc.csci310.project.watchlist.watchlistController.decrypt;
import static edu.usc.csci310.project.watchlist.watchlistController.encrypt;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class watchlistControllerTest {

    @Test
    public void createListFirst()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);
        String answer = "{\"success\": \"" + "false" + "\"," +
                "\"alreadyexists\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(answer, wc.createList("temp", "temp", "temp"));
    }

    @Test
    public void createListSecond()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        ArrayList<String> templist = new ArrayList<String>();
        templist.add("random");

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getWatchList(anyString())).thenReturn(templist);

        String expected = "{\"success\": \"" + "false" + "\"," +
            "\"alreadyexists\": \"" + "true" + "\"," +
            "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.createList("temp", "temp", "temp"));
    }

    @Test
    public void createListThird()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getWatchList(anyString())).thenReturn(null);

        doNothing().when(mockuser).createnewList(anyString());
        when(mockuser.addtowatchlist(anyString(), anyString())).thenReturn(true);

        String expected = "{\"success\": \"" + "true" + "\"," +
                "\"failure\": \"" + "false" + "\"}";
        assertEquals(expected, wc.createList("temp", "temp", "temp"));
    }

    @Test
    public void addtoListFirst()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);

        String expected = "{\"success\": \"" + "false" + "\"," +
            "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListSecond()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListThird()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        HashMap<String, ArrayList<String>> mockhashmap = mock(HashMap.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.get(anyString())).thenReturn(new ArrayList<String>());
        when(mockuser.addtowatchlist(anyString(), anyString())).thenReturn(true);

        String expected = "{\"success\": \"" + "true" + "\"," +
                "\"failure\": \"" + "false" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListFourth()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        HashMap<String, ArrayList<String>> mockhashmap = mock(HashMap.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.get(anyString())).thenReturn(new ArrayList<String>());
        when(mockuser.addtowatchlist(anyString(), anyString())).thenReturn(false);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListFifth()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        HashMap<String, ArrayList<String>> mockhashmap = mock(HashMap.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.get(anyString())).thenReturn(null);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void showallListsFirst()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);
        String expected = "[]";
        assertEquals(expected, wc.showallLists("temp"));
    }

    @Test
    public void showallListsSecond()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        String expected = "[]";
        assertEquals(expected, wc.showallLists("temp"));
    }

    @Test
    public void showallListsThird()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        HashMap<String, ArrayList<String>> mockhashmap = mock(HashMap.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.isEmpty()).thenReturn(true);

        String expected = "[]";
        assertEquals(expected, wc.showallLists("temp"));
    }

    @Test
    public void showallListsFourth()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        HashMap<String, ArrayList<String>> mockhashmap = mock(HashMap.class);
        Set<String> tempset = new HashSet<String>();
        tempset.add("first");
        tempset.add("second");

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.isEmpty()).thenReturn(false);
        when(mockhashmap.keySet()).thenReturn(tempset);

        String decryptfirst = decrypt("first");
        String decrppysecond = decrypt("second");

        String expected = "[ " + decryptfirst + ", " + decrppysecond + "]";
        assertEquals(expected, wc.showallLists("temp"));
    }

    @Test
    public void encryptFirst()
    {
        String input = "temp";
        String expected = "yjru";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptSecond()
    {
        String input = "TEMP";
        String expected = "YJRU";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptThird()
    {
        String input = "123";
        String expected = "678";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptFourth()
    {
        String input = "@!!";
        String expected = "@!!";
        assertEquals(expected, encrypt(input, 5));
    }

    @Test
    public void encryptFifth()
    {
        String input = "";
        String expected = "";
        assertEquals(expected, encrypt(input, 5));
    }


    @Test
    public void decryptFirst()
    {
        String input  = "yjru";
        String expected = "temp";
        assertEquals(expected, decrypt(input));
    }
}
