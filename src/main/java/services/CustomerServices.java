package services;


import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import util.Constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.customer.UpdateCustomerCommand;
import command.customer.GetCustomerByIDCommand;
import command.customer.GetCustomerByNameCommand;
import command.customer.ListCustomerCommand;
import command.customer.CreateCustomerCommand;
import command.customer.DeleteCustomerCommand;
import model.Customer;




@Path("customer")
public class CustomerServices {
	ObjectMapper mapper = new ObjectMapper();

	// Browse all customers
		@GET
//		@Produces({ MediaType.APPLICATION_JSON })
		public Response browseCustomers(@QueryParam("offset") int offset,
				@QueryParam("count") int count) {
			ListCustomerCommand command = new ListCustomerCommand();
			ArrayList<Customer> list = command.execute();
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("Customer", list);
			return Response.ok(new Viewable("/customer/DisplayAllC.jsp", hm)).build();
//			hm.put(Constants.Pagination.DATA, list);
//			hm.put(Constants.Pagination.OFFSET, offset);
//			hm.put(Constants.Pagination.COUNT, count);
//			String customerString = null;
//			try {
//				customerString = mapper.writeValueAsString(hm);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return Response.status(200).entity(customerString).build();
		}
		
		// get customers by Id or name
		@GET
		@Path("/get")
//		@Produces({ MediaType.APPLICATION_JSON })
		public Response getCustomers(@DefaultValue("nothing") @QueryParam("nid") String nid,
								@DefaultValue("nothing") @QueryParam("name") String name) {
			
			if(nid.equals("nothing") && name.equals("nothing")) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
			} else if(name.equals("nothing")) {
				GetCustomerByIDCommand command = new GetCustomerByIDCommand();
				return Response.ok(new Viewable("/customer/DisplayC.jsp", command.execute(nid))).build();
			} else {
				GetCustomerByNameCommand command = new GetCustomerByNameCommand();
				return Response.ok(new Viewable("/customer/DisplayC.jsp", command.execute(name))).build();
			}
			
			
//			if(nid.equals("nothing") && name.equals("nothing")) {
//				return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
//			} else if(name.equals("nothing")) {
//				GetCustomerByIDCommand command = new GetCustomerByIDCommand();
//				String customerString = null;
//				try {
//					customerString = mapper.writeValueAsString(command.execute(nid));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return Response.status(200).entity(customerString).build();
//			} else {
//				GetCustomerByNameCommand command = new GetCustomerByNameCommand();
//				String customerString = null;
//				try {
//					customerString = mapper.writeValueAsString(command.execute(name));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return Response.status(200).entity(customerString).build();
//			}
		}
		
	
		// Add a customer
		@POST
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
		public Response createCustomer(String payload){//,@FormParam("username") String username, @FormParam("password") String password) {
			CreateCustomerCommand create = new CreateCustomerCommand();
			Customer c = null;
			String i = "";
			try {
				c = mapper.readValue(payload, Customer.class);
			} catch (Exception ex) {
				ex.printStackTrace();
				Response.status(400).entity("could not read string").build();
			}
			try {
				i = create.execute(c,"mohan","12345");
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).entity(i).build();
		}
		
		// Update a song
		@POST
		@Path("{nid}")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
		public Response updateCustomer(String payload, @PathParam("nid") String nid) {
			UpdateCustomerCommand update = new UpdateCustomerCommand();
			Customer t = null;
			try {
				t = mapper.readValue(payload, Customer.class);
				t.setNid(nid);
			} catch (Exception ex) {
				ex.printStackTrace();
				Response.status(400).entity("could not read string").build();
			}
			try {
				update.execute(t);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).build();
		}
		
		// Delete a customer
		@DELETE
		@Path("{nid}")
		public Response deleteCustomer(@PathParam("nid") String nid) {
			DeleteCustomerCommand delete = new DeleteCustomerCommand();
			try {
				delete.execute(nid);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).build();
		}
}
