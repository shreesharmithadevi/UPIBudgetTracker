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

}

