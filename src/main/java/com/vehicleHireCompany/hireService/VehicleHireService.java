package com.vehicleHireCompany.hireService;

import com.vehicleHireCompany.hireService.dao.HireDAO;
import com.vehicleHireCompany.hireService.exceptions.RetrievingDataException;
import com.vehicleHireCompany.hireService.dao.VehicleDAO;
import com.vehicleHireCompany.hireService.dto.Hire;
import com.vehicleHireCompany.hireService.dto.HireRate;
import com.vehicleHireCompany.hireService.dto.Vehicle;
import com.vehicleHireCompany.hireService.exceptions.InputParameterException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("vehicle")
public class VehicleHireService {
    @Context
    private UriInfo context;
    
    public static CopyOnWriteArrayList<Vehicle> vehicles;
    
    public static CopyOnWriteArrayList<HireRate> hireCosts;

    /** Creates a new instance of HelloWorld */
    public VehicleHireService() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        HireDAO hireDAO = new HireDAO();
        try {
            List<Vehicle> vehiclesList = vehicleDAO.readFile("vehicles.json").get();
            vehicles = new CopyOnWriteArrayList(vehiclesList);
        } catch (RetrievingDataException ex) {
            Logger.getLogger(VehicleHireService.class.getName()).log(Level.SEVERE, "Error reading vehicles file.", ex);
            System.exit(0);
        }
        
        try {
            List<HireRate> hireCostList = hireDAO.readFile("hire.json").get();
            hireCosts = new CopyOnWriteArrayList(hireCostList);
        } catch (RetrievingDataException ex) {
            Logger.getLogger(VehicleHireService.class.getName()).log(Level.SEVERE, "Error reading hire cost file.", ex);
            System.exit(0);
        }
    }

    @GET
    @Produces("application/json")
    public Response getHtml() {
        MultivaluedMap<String, String> params = context.getQueryParameters();
        Controller controller = new Controller();
        
        try {
            String json = controller.handleRequest("getVehicles", "", params);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (RetrievingDataException | InputParameterException e) {
            Logger.getLogger(VehicleHireService.class.getName()).log(Level.SEVERE, "Error getting vehicles.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Entity not found for UUID: ").build();
        }
    }
    
    @GET
    @Path("hireRate")
    @Produces("application/json")
    public Response getHireRate() {
        MultivaluedMap<String, String> params = context.getQueryParameters();
        Controller controller = new Controller();

        try {
            String json = controller.handleRequest("getHireRate", "", params);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (RetrievingDataException | InputParameterException e) {
            Logger.getLogger(VehicleHireService.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Entity not found for UUID: ").build();
        }
    }
}