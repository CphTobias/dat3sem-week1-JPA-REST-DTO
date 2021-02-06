package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;
import facades.CustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bankcustomer")
public class BankCustomerResource {

    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private final CustomerFacade facade =  CustomerFacade.getCustomerFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") long id) {
        CustomerDTO customerDTOS = facade.getCustomerById(id);
        return Response.ok(gson.toJson(customerDTOS)).build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<BankCustomer> customers = facade.getAllBankCustomers();
        return Response.ok(gson.toJson(customers)).build();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersByName(
        @PathParam("name") String name
    ) {
        List<CustomerDTO> customerDTOS = facade.getCustomersByName(name);
        return Response.ok(gson.toJson(customerDTOS)).build();
    }
}
