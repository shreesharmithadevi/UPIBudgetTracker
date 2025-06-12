package com.budgetupi.upiplatform.Dto;

public class CategoryDto {
    private double spent;
    public String name;
    public double budgetLimit;

    public CategoryDto(String name, double budgetLimit, double spent) {
        this.name = name;
        this.budgetLimit = budgetLimit;
        this.spent = spent;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }
}
