package no.ntnu.idatt2105.calculator.repository;

import no.ntnu.idatt2105.calculator.model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    List<Calculation> findByUserUsername(String username);
}

