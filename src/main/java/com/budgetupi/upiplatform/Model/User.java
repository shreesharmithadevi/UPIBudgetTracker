package com.budgetupi.upiplatform.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phone;
    private int age;
    private String bankname;
    private String password;
    private String confrimpassword;
    private String pin;
    private String upiId;
    private double salary;
    private double totalSpent=0;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Category> categories;

    public long getId() {
        return id;
    }

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
        this.age=age;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfrimpassword() {
        return confrimpassword ;
    }

    public void setConfrimpassword(String confrimpassword) {
        this.confrimpassword=confrimpassword;
    }


    public void setPin(String pin) {
        this.pin=pin;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getUpiId() {
        return upiId;
    }
    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }
}
