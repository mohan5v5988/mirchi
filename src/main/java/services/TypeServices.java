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

import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	@GET
	@Path("metadata")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSongMeta() {
		Type t = new Type();
		try {
			@SuppressWarnings("unchecked")
			HashMap songHM = mapper.convertValue(t, HashMap.class);
			return Response.status(200).entity(mapper.writeValueAsString(songHM)).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	
	
	// Browse all Types
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		public Response browseTypes(@QueryParam("offset") int offset,
				@QueryParam("count") int count) {
			ListTypeCommand command = new ListTypeCommand();
			ArrayList<Type> list = command.execute();
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
			return Response.status(201).build();
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
