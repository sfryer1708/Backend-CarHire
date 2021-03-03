package com.vehicleHireCompany.hireService.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicleHireCompany.hireService.VehicleHireService;
import com.vehicleHireCompany.hireService.dto.Hire;
import com.vehicleHireCompany.hireService.dto.HireRate;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import com.vehicleHireCompany.hireService.utils.FileUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HireDAO {
    public Optional<List<HireRate>> readFile(final String file) throws RetrievingDataException {
        Optional<List<HireRate>> hires = Optional.empty();
        ObjectMapper mapper = new ObjectMapper();
        FileUtils fileUtils = new FileUtils();
        try(InputStream is = fileUtils.getFileFromResourceAsStream(Optional.ofNullable(file))) {
            List<HireRate> hiresTemp = mapper.readValue(is, mapper.getTypeFactory().
                 constructCollectionType(List.class, HireRate.class));
            hires = Optional.of(hiresTemp);
        } catch(FileNotFoundException e) {
            throw new RetrievingDataException(e);
        } catch(IOException e) {
            throw new RetrievingDataException(e);
        }

        return hires;
    }
    
    public long getHireCost(final String category) {
        final List<HireRate> list = VehicleHireService.hireCosts.stream().
            filter(e -> e.getCategory().equals(category)).collect(Collectors.toList());
        return list.size() > 0 ? list.get(0).getRate() : -1;
    }
}
