package com.vehicleHireCompany.hireService.dto;

public class HireRate {
    private String category;
    
    private long rate;
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }
}
