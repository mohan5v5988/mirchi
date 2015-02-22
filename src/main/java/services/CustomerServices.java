package services;


import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import util.Constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.customer.GetCustomerByIDCommand;
import command.customer.GetCustomerByNameCommand;
import command.customer.ListCustomerCommand;
import command.customer.CreateCustomerCommand;
import model.Customer;




@Path("customer")
public class CustomerServices {
	ObjectMapper mapper = new ObjectMapper();

	// Browse all customers
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		public Response browseSongs(@QueryParam("offset") int offset,
				@QueryParam("count") int count) {
			ListCustomerCommand command = new ListCustomerCommand();
			ArrayList<Customer> list = command.execute();
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put(Constants.Pagination.DATA, list);
			hm.put(Constants.Pagination.OFFSET, offset);
			hm.put(Constants.Pagination.COUNT, count);
			String songString = null;
			try {
				songString = mapper.writeValueAsString(hm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(songString).build();
		}
		
		// get customers by Id or name
		@GET
		@Path("/get")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getSong(@DefaultValue("nothing") @QueryParam("nid") String nid,
								@DefaultValue("nothing") @QueryParam("name") String name) {
			if(nid == "nothing" && name == "Nothing") {
				return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
			} else if(name == "nothing") {
				GetCustomerByIDCommand command = new GetCustomerByIDCommand();
				String songString = null;
				try {
					songString = mapper.writeValueAsString(command.execute(nid));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Response.status(200).entity(songString).build();
			} else {
				GetCustomerByNameCommand command = new GetCustomerByNameCommand();
				String songString = null;
				try {
					songString = mapper.writeValueAsString(command.execute(nid));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Response.status(200).entity(songString).build();
			}
		}
		
	
	// Add a customer
		@POST
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		public Response createSongs(String payload) {
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
				i = create.execute(c);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).entity(i).build();
		}
}
