package com.vehicleHireCompany.hireService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface VehicleHireService {
    
    @GET
    @Produces("application/json")
    public Response getVehicles();
    
    @GET
    @Path("hireRate")
    @Produces("application/json")
    public Response getHireRate();
}
