package controllers;

import domain.Customer;
import org.glassfish.jersey.media.multipart.FormDataParam;
import services.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cs")
public class CustomerController{
    private CustomerService cs;

    public CustomerController(){
        cs=new CustomerService();
    }
    @GET
    public String hello(){
        return "Hello, world!";
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/add")
    public Response addCustomer(@FormDataParam("fname")String fname,
                                @FormDataParam("lname")String lname,
                                @FormDataParam("email")String email,
                                @FormDataParam("password")String password){
        cs.addUser(fname,lname,email,password);
        return Response.status(200).entity("Customer is created").build();
    }
    @GET
    @Path("{id}")
    public Response getCustomerByID(@PathParam("id")long id){
        Customer customer=cs.getCustomer(id);
        if(customer==null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("There is no customer")
                    .build();
        }
        else{
            return Response
                    .status(Response.Status.OK)
                    .entity(customer)
                    .build();
        }
    }
}
