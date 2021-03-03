package com.vehicleHireCompany.hireService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicleHireCompany.hireService.dao.VehicleDAO;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import com.vehicleHireCompany.hireService.dao.VehicleDAOImpl;
import com.vehicleHireCompany.hireService.dto.Hire;
import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.exceptions.InputParameterException;
import com.vehicleHireCompany.hireService.hire.VehicleHireImpl;
import com.vehicleHireCompany.hireService.utils.DateUtils;
import com.vehicleHireCompany.hireService.utils.JSONUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;

public class Controller {
    
    public String handleRequest(final String action, final String data, 
            final MultivaluedMap<String, String> params) 
                throws RetrievingDataException, InputParameterException {
        
        String json = null;        
        VehicleDAO vehicleDAO = new VehicleDAOImpl();
        
        switch(action) {
            case "getVehicles": {
                Optional<List<Vehicle>> vehicles = Optional.empty();
                JSONUtils jsonUtils = new JSONUtils();
                
                if(params.getFirst("onlyAvailable").equals("true")) {
                    vehicles = Optional.of(vehicleDAO.getAvaiableVehicles());
                } else {
                    vehicles = Optional.of(vehicleDAO.getAllVehicles());
                }
                
                if(vehicles.isPresent()) {
                    json = jsonUtils.convertListToJson(vehicles.get());
                }
                break;   
            }
            case "getHireRate": {
                Optional<Vehicle> vehicle = Optional.empty();
                double hireCost = -1;
                
                if(params.getFirst("regNumber") != null && 
                    params.getFirst("start") != null &&
                    params.getFirst("end") != null) {
                    
                    vehicle = vehicleDAO.getVehicle(Optional.of(params.getFirst("regNumber")));
                    VehicleHireImpl vehicleHire = new VehicleHireImpl();
                    DateUtils dateUtils = new DateUtils();
                    Optional<LocalDate> startDate = dateUtils.createLocalDate(params.getFirst("start"));
                    Optional<LocalDate> endDate = dateUtils.createLocalDate(params.getFirst("end"));
                    hireCost = vehicleHire.calculateHireCost(Optional.of(params.getFirst("regNumber")), startDate, endDate);
                }
                
                if(vehicle.isPresent() && hireCost > 0) {
                    Hire hire = new Hire();
                    hire.setVehicle(vehicle.get());
                    hire.setRate(hireCost);
                    hire.setCategory(vehicle.get().getCategory());
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        json = mapper.writeValueAsString(hire);
                    } catch (JsonProcessingException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                break;
            }
            default: 
                break;
        }
        
        return json;
    }
}
