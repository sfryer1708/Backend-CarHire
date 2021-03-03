package com.vehicleHireCompany.hireService.hire;

import com.vehicleHireCompany.hireService.dao.HireDAO;
import com.vehicleHireCompany.hireService.dao.VehicleDAO;
import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import java.time.Duration;
import java.time.LocalDate;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javamoney.moneta.Money;

public class VehicleHire {
    
    public double calculateHireCost(Optional<String> regNumber, Optional<LocalDate> start, 
        Optional<LocalDate> end) {
        
        CurrencyUnit gbp = Monetary.getCurrency("GBP");
        Money hireMoney = Money.of(-1, gbp);
        
        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            HireDAO hireDAO = new HireDAO();
            Optional<Vehicle> vehicle = vehicleDAO.getVehicle(regNumber);
            Optional<String> category = Optional.empty();
            
            if(vehicle.isPresent()) {
                category = Optional.ofNullable(vehicle.get().getCategory());
            }
            
            if(category.isPresent() && start.isPresent() && end.isPresent()) {
                Duration diff = Duration.between(start.get().atStartOfDay(), 
                     end.get().atStartOfDay());
                int days = (int)diff.toDays();
                
                long hireCost = hireDAO.getHireCost(category.get());
                hireMoney = Money.of(hireCost, gbp);
                hireMoney = hireMoney.multiply(days);
            }
            
            return hireMoney.divide(100).getNumber().doubleValue();
        } catch (RetrievingDataException ex) {
            Logger.getLogger(VehicleHire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hireMoney.getNumber().doubleValue();
    }
}
