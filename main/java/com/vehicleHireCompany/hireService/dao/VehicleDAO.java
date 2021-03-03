/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicleHireCompany.hireService.dao;

import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import java.util.List;

/**
 *
 * @author sfrye
 */
public interface VehicleDAO {
    public List<Vehicle> getAvaiableVehicles() throws RetrievingDataException;
}
