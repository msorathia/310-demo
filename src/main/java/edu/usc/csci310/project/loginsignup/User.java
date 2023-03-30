package edu.usc.csci310.project.loginsignup;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "watchlists")
    private HashMap<String, ArrayList<String>> watchlists;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.watchlists = new HashMap<String, ArrayList<String>>();
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, ArrayList<String>> getAllWatchlists()
    {
        return watchlists;
    }

    public ArrayList<String> getWatchList(String watchlistname)
    {
        if(watchlists != null)
            return watchlists.get(watchlistname);
        return null;
    }

    public void createnewList(String watchlistname)
    {
        ArrayList<String> newlist = new ArrayList<String>();
        if(watchlists == null)
            watchlists = new HashMap<String,ArrayList<String>>();
        watchlists.put(watchlistname, newlist);
    }

    public boolean addtowatchlist(String id, String listname)
    {
        ArrayList<String> retrievedlist = watchlists.get(listname);
        if(retrievedlist == null)
        {
            return false;
        }
        else
        {
            retrievedlist.add(id);
            return true;
        }

    }

}