package com.vehicleHireCompany.hireService.dao;

import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import java.util.List;
import java.util.Optional;

public interface VehicleDAO {
    public List<Vehicle> getAvaiableVehicles() throws RetrievingDataException;
    
    public List<Vehicle> getAllVehicles() throws RetrievingDataException;
    
    public Optional<Vehicle> getVehicle(final Optional<String> registration) throws RetrievingDataException;
}
