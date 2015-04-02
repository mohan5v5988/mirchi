package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Due;
import model.Payments;
import command.payments.*;

@Path("payment")
public class PaymentsServices {
	ObjectMapper mapper = new ObjectMapper();
	
	@GET
	@Path("metadata")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSongMeta() {
		Payments p = new Payments();
		try {
			@SuppressWarnings("unchecked")
			HashMap songHM = mapper.convertValue(p, HashMap.class);
			return Response.status(200).entity(mapper.writeValueAsString(songHM)).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	// get all
	@GET
	@Produces({ MediaType.APPLICATION_JSON , MediaType.TEXT_PLAIN})
	public Response getTransactions() {
		ArrayList<Payments> arr = new ArrayList<Payments>();
		String paymentString = null;
		ListAllPayments command = new ListAllPayments();
		arr = command.execute();
		try {
			paymentString = mapper.writeValueAsString(arr);
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity("Please check the values").build();
		}
		return Response.status(Response.Status.OK).entity(paymentString).build();
	}
	
	// get by nid, date, id and "nid and date".
	@GET
	@Path("/get")
//	@Produces({ MediaType.APPLICATION_JSON , MediaType.TEXT_PLAIN})
	public Response getTransactions(@DefaultValue("nothing") @QueryParam("nid") String nid,
							@DefaultValue("0") @QueryParam("id") int id,
							@DefaultValue("1963-12-22") @QueryParam("date") String date) {
		ArrayList<Payments> arr = new ArrayList<Payments>();
		String paymentString = null;
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		if(nid.equals("nothing") && date.equals("1963-12-22") && id == 0) {
//			return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
//		} else if(nid.equals("nothing") ^ date.equals("1963-12-22")) {
//			if(nid.equals("nothing")) {
//				GetPaymentsByDateCommand command = new GetPaymentsByDateCommand();
//				hm.put("Payments", command.execute(date));
//			} else if(date.equals("1963-12-22")){
//				GetPaymentsByNidCommand command = new GetPaymentsByNidCommand();
//				hm.put("Payments", command.execute(nid));
//			}
//		} else if(id == 0) {
//			GetPaymentsByNidandDateCommand command = new GetPaymentsByNidandDateCommand();
//			hm.put("Payments", command.execute(nid, date));
//		} else {
//			GetPaymentsByIdCommand command = new GetPaymentsByIdCommand();
//			arr.add(command.execute(id));
//			hm.put("Payments", arr);
//		}
//		return Response.ok(new Viewable("/payments/DisplayP.jsp", hm)).build();
		
		
		
		if(nid.equals("nothing") && date.equals("1963-12-22") && id == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
		} else if(nid.equals("nothing") ^ date.equals("1963-12-22")) {
			if(nid.equals("nothing")) {
				GetPaymentsByDateCommand command = new GetPaymentsByDateCommand();
				arr = command.execute(date);
				try {
					paymentString = mapper.writeValueAsString(arr);
				} catch(Exception e) {
					e.printStackTrace();
					return Response.status(Response.Status.BAD_REQUEST).entity("Please check the values").build();
				}
			} else if(date.equals("1963-12-22")){
				GetPaymentsByNidCommand command = new GetPaymentsByNidCommand();
				arr = command.execute(nid);
				try {
					paymentString = mapper.writeValueAsString(arr);
				} catch(Exception e) {
					e.printStackTrace();
					return Response.status(Response.Status.BAD_REQUEST).entity("Please check the values").build();
				}
			}
		} else if(id == 0) {
			GetPaymentsByNidandDateCommand command = new GetPaymentsByNidandDateCommand();
			arr = command.execute(nid, date);
			try {
				paymentString = mapper.writeValueAsString(arr);
			} catch(Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity("Please check the values").build();
			}
		} else {
			GetPaymentsByIdCommand command = new GetPaymentsByIdCommand();
			Payments a = command.execute(id);
			arr.add(a);
			try {
				paymentString = mapper.writeValueAsString(arr);
			} catch(Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity("Please check the values").build();
			}
		}
		return Response.status(Response.Status.OK).entity(paymentString).build();
	}
	
	// get with two dates and nid.
	@GET
	@Path("/date/{date1}/{date2}")
//	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPaymentByDatesOrNid(@PathParam("date1") String date1,@PathParam("date2") String date2,
			@DefaultValue("nothing") @QueryParam("nid") String nid) {
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		if(nid.equals("nothing")) {
//			GetPaymentsBetweenDatesCommand command = new GetPaymentsBetweenDatesCommand();
//			hm.put("Payments", command.execute(date1,date2));
//		}else {
//			GetPaymentsBetweenDatesAndNidCommand command = new GetPaymentsBetweenDatesAndNidCommand();
//			hm.put("Payments", command.execute(date1,date2,nid));
//		}
//		return Response.ok(new Viewable("/payments/DisplayP.jsp", hm)).build();
		if(nid.equals("nothing")) {
			GetPaymentsBetweenDatesCommand command = new GetPaymentsBetweenDatesCommand();
			String paymentString = null;
			try {
				paymentString = mapper.writeValueAsString(command.execute(date1,date2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(paymentString).build();
		}else {
			GetPaymentsBetweenDatesAndNidCommand command = new GetPaymentsBetweenDatesAndNidCommand();
			String paymentString = null;
			try {
				paymentString = mapper.writeValueAsString(command.execute(date1,date2,nid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(paymentString).build();
		}
	}
	
	
	// Add a Payment
		@POST
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
		public Response createPayment(String payload) {
			CreatePaymentCommand create = new CreatePaymentCommand();
			Payments p = null;
			String i = "";
			try {
				p = mapper.readValue(payload, Payments.class);
			} catch (Exception ex) {
				ex.printStackTrace();
				Response.status(400).entity("could not read string").build();
			}
			try {
				i = create.execute(p);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).entity(i).build();
		}
	
	// Update a Payment
		@POST
		@Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
		public Response updatePayment(String payload, @PathParam("id") int id) {
			UpdatePaymentsCommand update = new UpdatePaymentsCommand();
				Payments p = null;
			try {
					p = mapper.readValue(payload, Payments.class);
					p.setId(id);
			} catch (Exception ex) {
				ex.printStackTrace();
				Response.status(400).entity("could not read string").build();
			}
			try {
					update.execute(p);
			} catch (Exception e) {
					e.printStackTrace();
					Response.status(500).build();
			}
			return Response.status(200).build();
		}
	
		// Delete a Payment
		@DELETE
		@Path("{id}")
		public Response deletePayment(@PathParam("id") int id) {
			DeletePaymentsCommand delete = new DeletePaymentsCommand();
			try {
				System.out.println(id);
				delete.execute(id);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).build();
		}
		
		@GET
		@Path("/due")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getDueAmount() {
			GetDueNid command = new GetDueNid();
			String dueString = null;
			ArrayList<Due> d = command.execute();
			for(Due a : d) {
				System.out.println(a);
			}
			try {
				dueString = mapper.writeValueAsString(d);
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			return Response.status(200).entity(dueString).build();
		}
		
		@GET
		@Path("/due/{nid}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getDueAmount(@PathParam("nid") String nid) {
			GetDueByNidCommand command = new GetDueByNidCommand();
//			return Response.ok(new Viewable("/due/due.jsp", command.execute(nid))).build();
			String dueString = null;
			try {
				dueString = mapper.writeValueAsString(command.execute(nid));
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			return Response.status(200).entity(dueString).build();
		}
}