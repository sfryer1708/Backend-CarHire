/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicleHireCompany.hireService.utils;

import com.vehicleHireCompany.hireService.VehicleHireService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtils {
    
    public Optional<LocalDate> createLocalDate(String date) {
        
        Optional<LocalDate> localDate = Optional.empty();
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return Optional.of(LocalDate.parse(date, formatter));
        } catch(IllegalArgumentException | DateTimeParseException e) {
            Logger.getLogger(VehicleHireService.class.getName()).log(
                    Level.SEVERE, "Error parsing date.", e);
        }
        
        return localDate;
    }
}