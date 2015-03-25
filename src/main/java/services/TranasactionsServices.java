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

import model.Calculation;
import model.HelpingMethods;
import model.Tranasactions;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.tranasactions.CreateTranasactionsCommand;
import command.tranasactions.DeleteTranasactionsByTidCommand;
import command.tranasactions.GetTranasactionsBetweenDatesAndNidCommand;
import command.tranasactions.GetTranasactionsBetweenDatesCommand;
import command.tranasactions.GetTranasactionsByNidCommand;
import command.tranasactions.GetTranasactionsByNidandDateCommand;
import command.tranasactions.GetTranasactionsByTidCommand;
import command.tranasactions.GetTranasactionsByDateCommand;
import command.tranasactions.UpdateTranasactionsCommand;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.*;

@Path("transaction")
public class TranasactionsServices {
	ObjectMapper mapper = new ObjectMapper();
	
	// get Transaction by tid , nid ,date, "date and nid".
	@GET
	@Path("/get")
//	@Produces({ MediaType.APPLICATION_JSON , MediaType.TEXT_PLAIN})
	public Response getTransactions(@DefaultValue("nothing") @QueryParam("nid") String nid,
							@DefaultValue("0") @QueryParam("tid") int tid,
							@DefaultValue("1963-12-22") @QueryParam("date") String date) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		if(nid.equals("nothing") && date.equals("1963-12-22") && tid == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
		} else if(nid.equals("nothing") ^ date.equals("1963-12-22")) {
			if(nid.equals("nothing")) {
				GetTranasactionsByDateCommand command = new GetTranasactionsByDateCommand();
				hm.put("Tranasactions", HelpingMethods.setCalObjToTran(command.execute(date)));
			} else if(date.equals("1963-12-22")){
				GetTranasactionsByNidCommand command = new GetTranasactionsByNidCommand();
				hm.put("Tranasactions", HelpingMethods.setCalObjToTran(command.execute(nid)));
			}
		} else if(tid == 0) {
			GetTranasactionsByNidandDateCommand command = new GetTranasactionsByNidandDateCommand();
			hm.put("Tranasactions", HelpingMethods.setCalObjToTran(command.execute(nid, date)));
		} else {
			GetTranasactionsByTidCommand command = new GetTranasactionsByTidCommand();
			ArrayList<Tranasactions> arr = new ArrayList<Tranasactions>();
			arr.add(command.execute(tid));
			hm.put("Tranasactions", HelpingMethods.setCalObjToTran(arr));
		}
		return Response.ok(new Viewable("/tranasactions/DisplayAllT.jsp", hm)).build();
		
//		if(nid.equals("nothing") && date.equals("1963-12-22") && tid == 0) {
//			return Response.status(Response.Status.BAD_REQUEST).entity("Please enter any value to search.").build();
//		} else if(nid.equals("nothing") ^ date.equals("1963-12-22")) {
//			if(nid.equals("nothing")) {
//				GetTranasactionsByDateCommand command = new GetTranasactionsByDateCommand();
//				ArrayList<Tranasactions> arr = command.execute(date);
//				try {
//					return Response.status(200).entity(mapper.writeValueAsString(convertTarraytoString(arr))).build();
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			} else if(date.equals("1963-12-22")){
//				GetTranasactionsByNidCommand command = new GetTranasactionsByNidCommand();
//				ArrayList<Tranasactions> arr = command.execute(nid);
//				try {
//					return Response.status(200).entity(mapper.writeValueAsString(convertTarraytoString(arr))).build();
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} else if(tid == 0) {
//			GetTranasactionsByNidandDateCommand command = new GetTranasactionsByNidandDateCommand();
//			ArrayList<Tranasactions> arr = command.execute(nid, date);
//			try {
//				return Response.status(200).entity(mapper.writeValueAsString(convertTarraytoString(arr))).build();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			GetTranasactionsByTidCommand command = new GetTranasactionsByTidCommand();
//			try {
//				return Response.status(200).entity(convertTobjecttoString(command.execute(tid))).build();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return Response.status(Response.Status.BAD_REQUEST).entity("Please check the request.").build();
	}
	
	// get with two dates and nid.
		@GET
		@Path("/date/{date1}/{date2}")
//		@Produces({ MediaType.APPLICATION_JSON })
		public Response getPaymentByDatesOrNid(@PathParam("date1") String date1,@PathParam("date2") String date2,
				@DefaultValue("nothing") @QueryParam("nid") String nid) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			if(nid.equals("nothing")) {
				GetTranasactionsBetweenDatesCommand command = new GetTranasactionsBetweenDatesCommand();
				hm.put("Tranasactions", HelpingMethods.setCalObjToTran(command.execute(date1,date2)));
			}else {
				GetTranasactionsBetweenDatesAndNidCommand command = new GetTranasactionsBetweenDatesAndNidCommand();
				hm.put("Tranasactions", HelpingMethods.setCalObjToTran(command.execute(date1,date2,nid)));
			}
			return Response.ok(new Viewable("/tranasactions/DisplayAllT.jsp", hm)).build();
			
			
//			if(nid.equals("nothing")) {
//				GetTranasactionsBetweenDatesCommand command = new GetTranasactionsBetweenDatesCommand();
//				String tranasactionString = null;
//				try {
//					tranasactionString = mapper.writeValueAsString(convertTarraytoString(command.execute(date1,date2)));
//				} catch (Exception e) {
//					e.printStackTrace();
//					tranasactionString = "Please enter a valide dates";
//				}
//				return Response.status(200).entity(tranasactionString).build();
//			}else {
//				GetTranasactionsBetweenDatesAndNidCommand command = new GetTranasactionsBetweenDatesAndNidCommand();
//				String tranasactionString = null;
//				try {
//					tranasactionString = mapper.writeValueAsString(convertTarraytoString(command.execute(date1,date2,nid)));
//				} catch (Exception e) {
//					e.printStackTrace();
//					tranasactionString = "Please enter a valide dates";
//				}
//				return Response.status(200).entity(tranasactionString).build();
//			}
		}
	
	// Add a Tranasaction
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response createTransaction(String payload) {
		CreateTranasactionsCommand create = new CreateTranasactionsCommand();
		Tranasactions t = null;
				String i = "";
		try {
			t = getTObjectFronJSON(payload);
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(400).entity("could not read string").build();
		}
		try {
			create.execute(t);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}
	
	// Update a Tranasaction
	@POST
	@Path("/update/{tid}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response updateTransaction(String payload, @PathParam("tid") int tid) {
		UpdateTranasactionsCommand update = new UpdateTranasactionsCommand();
		Tranasactions t = null;
		try {
			t = getTObjectFronJSON(payload);
			t.setTid(tid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(400).entity("could not read string").build();
		}
		try {
			update.execute(t);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}
		
	// Delete a transaction
	@DELETE
	@Path("{tid}")
	public Response deleteTransaction(@PathParam("tid") int tid) {
		DeleteTranasactionsByTidCommand delete = new DeleteTranasactionsByTidCommand();
		try {
			delete.execute(tid);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}
	
	Tranasactions getTObjectFronJSON(String json) throws Exception {
		ArrayList<Double> d = new ArrayList<Double>();
		Tranasactions t = new Tranasactions();
		JSONObject myJSON = new JSONObject(json);
		JSONArray jsonArray = myJSON.getJSONArray("bagkg");
		if (jsonArray != null) { 
		   int len = jsonArray.length();
		   for (int m=0; m<len; m++){ 
			   d.add(jsonArray.getDouble(m));
		   } 
		} 
		Calculation c = new Calculation(myJSON.getInt("bags"),myJSON.getInt("coolie"),
										myJSON.getInt("railway"),d,
										myJSON.getDouble("rate"),myJSON.getDouble("vat"));
		t.setTid(myJSON.getInt("tid"));
		t.setNid(myJSON.getString("nid"));
		t.setType(myJSON.getString("type"));
		t.setDate(java.sql.Date.valueOf(myJSON.getString("date")));
		t.setJson(mapper.writeValueAsString(c));
		return t;
	}
	
	ArrayList<HashMap<String, Object>> convertTarraytoString(ArrayList<Tranasactions> arr) throws Exception {
		ArrayList<HashMap<String, Object>> harr = new ArrayList<HashMap<String, Object>>();
		for(int m=0;m<(arr.size()-1);m++) {
			HashMap<String, Object> hm = (HashMap<String, Object>) mapper.readValue(convertTobjecttoString(arr.get(m)),HashMap.class);
			harr.add(hm);
		}
		return harr;
	}
	
	String convertTobjecttoString(Tranasactions t) {
		try {
			JSONObject obj1 = new JSONObject(mapper.writeValueAsString(t));
			obj1.remove("json");
			JSONObject obj = new JSONObject(t.getJson());
			return obj1.toString().split("}")[0]+","+obj.toString().substring(1);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
}
