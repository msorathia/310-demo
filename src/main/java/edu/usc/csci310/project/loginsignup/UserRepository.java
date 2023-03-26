package edu.usc.csci310.project.loginsignup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}