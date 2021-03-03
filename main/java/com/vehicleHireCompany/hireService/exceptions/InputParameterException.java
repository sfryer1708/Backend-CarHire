package com.vehicleHireCompany.hireService.exceptions;

public class InputParameterException extends Exception {
    
    public InputParameterException(Exception e) {
        super("Error with input parameters.", e);
    }
}
