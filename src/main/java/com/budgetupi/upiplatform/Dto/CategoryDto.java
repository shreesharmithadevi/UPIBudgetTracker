package com.budgetupi.upiplatform.Dto;

public class CategoryDto {
    public String name;
    public double budgetLimit;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }
}
