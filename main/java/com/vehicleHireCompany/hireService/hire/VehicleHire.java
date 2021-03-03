package com.vehicleHireCompany.hireService.hire;

import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import java.time.LocalDate;
import java.util.Optional;

public interface VehicleHire {
    public double calculateHireCost(Optional<String> regNumber, Optional<LocalDate> start, 
        Optional<LocalDate> end) throws RetrievingDataException;
}
