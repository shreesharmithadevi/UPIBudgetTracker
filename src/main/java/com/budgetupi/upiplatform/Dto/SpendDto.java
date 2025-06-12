package com.budgetupi.upiplatform.Dto;

public class SpendDto {
    public long id;
    public String upiId;
    public String pin;
    public double spend;
    public String name;

    public long getId() {
        return id;
    }


    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getPin() {
        return pin;
    }

    public double getSpend() {
        return spend;
    }

    public String getName() {
        return name;
    }

}