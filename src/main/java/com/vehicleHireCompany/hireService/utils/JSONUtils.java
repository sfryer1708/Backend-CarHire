package com.vehicleHireCompany.hireService.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONUtils {
    
    public String convertListToJson(final List list) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(out, list);
        } catch (IOException ex) {
            Logger.getLogger(JSONUtils.class.getName()).log(Level.SEVERE, 
                 "Error converting list to JSON.", ex);
        }
        final byte[] data = out.toByteArray();
        return new String(data);
    }
}
