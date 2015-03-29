package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import command.type.CreateTypeCommand;
import command.type.UpdateTypeCommand;
import command.type.DeleteTypeCommand;
import command.type.GetTypeCommand;
import command.type.ListTypeCommand;
import model.Type;


@Path("type")
public class TypeServices {
	ObjectMapper mapper = new ObjectMapper();
	
	// Browse all Types
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		public Response browseTypes(@QueryParam("offset") int offset,
				@QueryParam("count") int count) {
			ListTypeCommand command = new ListTypeCommand();
			ArrayList<Type> list = command.execute();
//			HashMap<String, Object> hm = new HashMap<String, Object>();
//			hm.put("Type", list);
//			return Response.ok(new Viewable("/type/alltypes.jsp", list)).build();
//			hm.put(Constants.Pagination.DATA, list);
//			hm.put(Constants.Pagination.OFFSET, offset);
//			hm.put(Constants.Pagination.COUNT, count);
			String typeString = null;
			try {
				typeString = mapper.writeValueAsString(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(typeString).build();//typeString).build();
		}
		
		// get types by type
		@GET
		@Path("{type}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getType(@PathParam("type") String type) {
			GetTypeCommand command = new GetTypeCommand();
//			HashMap<String, Object> hm = new HashMap<String, Object>();
//			hm.put("Type", command.execute(type));
//			return Response.ok(new Viewable("/type/DisplyTbyID.jsp", hm)).build();
			String typeString = null;
			try {
				typeString = mapper.writeValueAsString(command.execute(type));
				System.out.println(typeString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(typeString).build();
		}
		
		// Add a type
		@POST
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
		public Response createType(String payload) {
			CreateTypeCommand create = new CreateTypeCommand();
			Type c = null;
			String i = "";
			try {
				c = mapper.readValue(payload, Type.class);
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
//			return Response.ok(new Viewable("/type/type.jsp")).build();
//			return Response.status(200).entity(i).build();
			return Response.status(200).build();
		}
		
		// Update a type
		@POST
		@Path("{type}")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
		public Response updateTypes(String payload, @PathParam("type") String type) {
			UpdateTypeCommand update = new UpdateTypeCommand();
			Type t = null;
			System.out.println("got it");
			try {
				t = mapper.readValue(payload, Type.class);
				t.setType(type);
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
			System.out.println("got it!!!!!");
			return Response.status(200).build();
		}
		
		// Delete a type
		@DELETE
		@Path("{type}")
		public Response deleteType(@PathParam("type") String type) {
			DeleteTypeCommand delete = new DeleteTypeCommand();
			try {
				delete.execute(type);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).build();
		}
}
