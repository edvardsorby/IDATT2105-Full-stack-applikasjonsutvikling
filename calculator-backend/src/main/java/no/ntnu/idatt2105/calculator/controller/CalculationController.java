package no.ntnu.idatt2105.calculator.controller;

import no.ntnu.idatt2105.calculator.model.Calculation;
import no.ntnu.idatt2105.calculator.repository.CalculationRepository;
import no.ntnu.idatt2105.calculator.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api")
public class CalculationController {
    @Autowired
    CalculationRepository calculationRepository;

    @Autowired
    private CalculationService calculationService;

    @GetMapping("/calculations")
    public ResponseEntity<List<Calculation>> getCalculations(@RequestParam(required = false) String username) {
        try {
            List<Calculation> calculations = new ArrayList<>();

            if (username == null)
                calculationRepository.findAll().forEach(calculations::add);
            else
                calculationRepository.findByUserUsername(username).forEach(calculations::add);

            if (calculations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(calculations, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/calculations")
    public ResponseEntity<Calculation> createCalculation(@RequestBody Calculation calculation) {
        try {

            String[] calculaltionArray = calculationService.stringToArray(calculation.getCalculation());
            String[] rpn = calculationService.infixToRPN(calculaltionArray);
            double answer = calculationService.rpnCalculator(rpn);


            Calculation _calculation = calculationRepository
                    .save(new Calculation(calculation.getCalculation(), calculation.getUser(), answer));
            return new ResponseEntity<>(_calculation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
