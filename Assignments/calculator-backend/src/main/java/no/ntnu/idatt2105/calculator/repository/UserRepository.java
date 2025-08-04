package no.ntnu.idatt2105.calculator.repository;

import java.util.List;
import java.util.Optional;

import no.ntnu.idatt2105.calculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
