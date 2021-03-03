package com.vehicleHireCompany.hireService.dao;

import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import com.vehicleHireCompany.hireService.VehicleHireServiceImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleDAOImpl implements VehicleDAO {
    
    public Optional<List<Vehicle>> readFile(final String file) throws RetrievingDataException {
        Optional<List<Vehicle>> vehicles = Optional.empty();
        ObjectMapper mapper = new ObjectMapper();
        FileUtils fileUtils = new FileUtils();
        try(InputStream is = fileUtils.getFileFromResourceAsStream(Optional.ofNullable(file))) {
            List<Vehicle> vehiclesTemp = mapper.readValue(is, mapper.getTypeFactory().
                 constructCollectionType(List.class, Vehicle.class));
            vehicles = Optional.of(vehiclesTemp);
        } catch(FileNotFoundException e) {
            throw new RetrievingDataException(e);
        } catch(IOException e) {
            throw new RetrievingDataException(e);
        }

        return vehicles;
    }
    
    @Override
    public List<Vehicle> getAvaiableVehicles() throws RetrievingDataException {
        final List<Vehicle> list = VehicleHireServiceImpl.vehicles.stream().filter(e -> e.isHired() == false).collect(Collectors.toList());
        return list;
    }
    
    @Override
    public List<Vehicle> getAllVehicles() throws RetrievingDataException {
        return VehicleHireServiceImpl.vehicles;
    }
    
    @Override
    public Optional<Vehicle> getVehicle(final Optional<String> registration) throws RetrievingDataException {
        if(registration.isPresent()) {
            final List<Vehicle> list = VehicleHireServiceImpl.vehicles.stream().filter(e -> e.getRegistration().equals(registration.get())).collect(Collectors.toList());
            return list.size() > 0 ? Optional.of(list.get(0)) : Optional.empty();
        }
        return Optional.empty();
    }
}
