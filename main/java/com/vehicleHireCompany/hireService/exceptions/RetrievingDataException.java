package com.vehicleHireCompany.hireService.exceptions;

public class RetrievingDataException extends Exception {
    
    public RetrievingDataException(Exception e) {
        super("Error retrieving data.", e);
    }
}
