package com.vehicleHireCompany.hireService.dto;

import java.text.DecimalFormat;

public class Hire {
    private String category;
    
    private String rate;
    
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(double rate) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.rate = df.format(rate);
    }
    
    public void setRate(String rate) {
        this.rate = rate;
    }
}
