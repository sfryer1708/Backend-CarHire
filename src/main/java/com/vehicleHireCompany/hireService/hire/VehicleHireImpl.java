package com.vehicleHireCompany.hireService.hire;

import com.vehicleHireCompany.hireService.dao.HireDAO;
import com.vehicleHireCompany.hireService.dao.HireDAOImpl;
import com.vehicleHireCompany.hireService.dao.VehicleDAO;
import com.vehicleHireCompany.hireService.dao.VehicleDAOImpl;
import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import java.time.Duration;
import java.time.LocalDate;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Optional;
import org.javamoney.moneta.Money;

public class VehicleHireImpl {
    
    public double calculateHireCost(Optional<String> regNumber, Optional<LocalDate> start, 
        Optional<LocalDate> end) throws RetrievingDataException {
        
        CurrencyUnit gbp = Monetary.getCurrency("GBP");
        Money hireMoney = Money.of(0, gbp);
        
        VehicleDAO vehicleDAO = new VehicleDAOImpl();
        HireDAO hireDAO = new HireDAOImpl();
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
    }
}
