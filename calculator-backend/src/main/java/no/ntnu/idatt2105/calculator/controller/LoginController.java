package no.ntnu.idatt2105.calculator.controller;

import no.ntnu.idatt2105.calculator.model.User;
import no.ntnu.idatt2105.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin({"http://localhost:5173", "http://10.0.0.11:5173"})
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public boolean login(@RequestParam String username, @RequestParam String password) {

        List<User> users = new ArrayList<>();


        userRepository.findAll().forEach(users::add);

        for (User user : users) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(password, user.getPassword())) {
                return true;
            }
        }

        return false;
    }


    @GetMapping("/test")
    public String test() {
        return "Hello there";
    }
}
