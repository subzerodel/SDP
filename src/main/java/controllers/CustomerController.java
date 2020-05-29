package controllers;

import domain.models.Customer;
import org.glassfish.jersey.media.multipart.FormDataParam;
import services.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("customer")
public class CustomerController {
    private CustomerService cs;

    public CustomerController() {
        cs = new CustomerService();
    }

    @GET
    public String hello() {
        return "Hello, world!";
    }

  @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/create")
    public Response addCustomer(@FormDataParam("fname") String fname,
                                @FormDataParam("lname") String lname,
                                @FormDataParam("email") String email,
                                @FormDataParam("password")String password) {
        cs.addCustomer(fname,lname,email,password);
        return Response.status(200).entity(fname+" is created").build();
    }
        //Qiyn joly!!! To do this thing you need to extend from Application

   /* @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/create")
    public Response CreateCustomer(Customer customer) {
        try {
            cs.addCustomer(customer);
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Created")
                .build();
    }*/
    //Onai joly!!! You need to extend from ResourceConfig


    @GET
    @Path("{id}")
    public Response getCustomerByID(@PathParam("id") long id) {
        Customer customer = cs.getCustomer(id);
        if (customer == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("There is no customer")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(customer)
                    .build();
        }
    }
}
