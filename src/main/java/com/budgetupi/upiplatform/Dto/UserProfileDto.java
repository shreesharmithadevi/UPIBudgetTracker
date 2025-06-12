package com.budgetupi.upiplatform.Dto;

import java.util.List;

public class UserProfileDto {
    private String name;
    private String upiId;
    private double salary;

    public UserProfileDto(String name, String upiId, double salary, List<CategoryDto> categories) {
        this.name = name;
        this.upiId = upiId;
        this.salary = salary;
        this.categories = categories;
    }

    private List<CategoryDto> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}

