package com.budgetupi.upiplatform.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double budgetLimit;
    private double spent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Category(String name, double budgetLimit, User user) {
        this.name=name;
        this.budgetLimit=budgetLimit;
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getBudgetLimit() {
        return budgetLimit;
    }
    public double getSpent() {
        return spent;
    }
    public void setSpent(double spent) {
        this.spent = spent;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit=budgetLimit;
    }
}
