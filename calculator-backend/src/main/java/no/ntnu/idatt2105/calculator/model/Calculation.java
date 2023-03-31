package no.ntnu.idatt2105.calculator.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "calculation")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "calculation")
    private String calculation;

    @Column(name = "answer")
    private double answer;

    @ManyToOne
    @JoinColumn(name="username", nullable=false)
    private User user;

    public Calculation() {
    }

    public Calculation(String calculation, User user, double answer) {
        this.calculation = calculation;
        this.user = user;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
