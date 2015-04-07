package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Due;

import com.sendgrid.*;

import command.customer.GetCustomerByIDCommand;
import command.payments.GetDueByNidCommand;
import command.payments.GetDueNid;
import util.APICredentials;

@Path("email")
public class EmailServices {

	SendGrid sendgrid = new SendGrid(APICredentials.sendgrid_username,
			APICredentials.sendgrid_password);

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response browseTypes() {
		GetDueNid command = new GetDueNid();
		ArrayList<Due> darr = command.execute();
		GetCustomerByIDCommand command1 = new GetCustomerByIDCommand();
		ArrayList<SendGrid.Email> earr = new ArrayList<SendGrid.Email>();
		for (Due d : darr) {
			SendGrid.Email email = new SendGrid.Email();
			email.addTo(command1.execute(d.getNid()).getEmail());
			email.setFrom("mohan5v5988@gmail.com");
			email.setSubject("About The Payment");
			email.setText("Hi, \n The amount you should give is "
					+ d.getAmount());
			earr.add(email);
		}
		for (SendGrid.Email email : earr) {
			try {
				SendGrid.Response response = sendgrid.send(email);
				// System.out.println(response.getMessage() +"  "+
				// response.getStatus());
			} catch (SendGridException e) {
				System.out.println(e);
			}
		}
		return Response.status(200).entity("").build();
	}

	@GET
	@Path("{nid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getType(@PathParam("nid") String nid) {
		GetDueByNidCommand command = new GetDueByNidCommand();
		Due d = command.execute(nid);
		GetCustomerByIDCommand command1 = new GetCustomerByIDCommand();
		SendGrid.Email email = new SendGrid.Email();
		email.addTo(command1.execute(nid).getEmail());
		email.setFrom("mohan5v5988@gmail.com");
		email.setSubject("About The Payment");
		email.setText("Hi, \n The amount you should give is " + d.getAmount());

		try {
			SendGrid.Response response = sendgrid.send(email);
			System.out.println(response.getMessage() + "  "
					+ response.getStatus());
		} catch (SendGridException e) {
			System.out.println(e);
		}

		return Response.status(200).entity("").build();
	}
//	public static void main(String[] args) {
//		EmailServices e = new EmailServices();
//		e.getType("mv");
//	}
}
