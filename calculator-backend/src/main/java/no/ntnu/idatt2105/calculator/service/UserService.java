package no.ntnu.idatt2105.calculator.service;

import no.ntnu.idatt2105.calculator.model.User;
import no.ntnu.idatt2105.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean checkUserCredentials(final String username, final String password) {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()) {
            User foundUser = user.get();

            if(foundUser.getUsername().equals(username) && foundUser.getPassword().equals(password))  {
                return true;
            }
        }
        return false;
    }

}
