package com.budgetupi.upiplatform.Dto;

public class UserDto {
    public String name;
    public int age;
    public String phone;
    public String bankname;
    public String password;
    public String confrimpassword;
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

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankName() {
        return bankname;
    }

    public void setBankName(String bankname) {
        this.bankname = bankname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfrimpassword() {
        return confrimpassword;
    }

    public void setConfrimpassword(String confrimpassword) {
        this.confrimpassword = confrimpassword;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
