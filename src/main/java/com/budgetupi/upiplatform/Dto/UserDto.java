package com.budgetupi.upiplatform.Dto;

public class UserDto {
    public String name;
    public int age;
    public String phone;
    public String bankname;
    public String password;
    public String confirmpassword;
    public String pin;
    public double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getBankName() {
        return bankname;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public String getPin() {
        return pin;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
