package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import util.Constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.type.GetTypeCommand;
import command.type.ListTypeCommand;
import model.Type;



@Path("type")
public class TypeServices {
	ObjectMapper mapper = new ObjectMapper();
	
	// Browse all types
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		public Response browseSongs(@QueryParam("offset") int offset,
				@QueryParam("count") int count) {
			ListTypeCommand command = new ListTypeCommand();
			ArrayList<Type> list = command.execute();
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
		
		// get types by type
		@GET
		@Path("{type}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getSong(@PathParam("type") String type) {
			GetTypeCommand command = new GetTypeCommand();
			String songString = null;
			try {
				songString = mapper.writeValueAsString(command.execute(type));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(songString).build();
		}
}
