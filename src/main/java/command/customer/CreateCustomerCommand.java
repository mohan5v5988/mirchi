package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import connectionprovider.ConnectionProvider;

public class CreateCustomerCommand {
	public String execute(Customer c) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO CUSTOMER(nid, name, address, pnumber) VALUES(?, ?, ?, ?) Returning nid");
			stmt.setString(1, c.getNid());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getAddress());
			stmt.setLong(4, c.getPnumber());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				PreparedStatement stmt1 = connection
//						.prepareStatement("CREATE TABLE "+rs.getString("nid")+" (sno serial PRIMARY KEY,type char,date date NOT NULL,amount double precision NOT NULL )");
//				stmt1.execute();
				return rs.getString("nid");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
//	public static void main(String[] args) {
//		CreateCustomerCommand c = new CreateCustomerCommand();
//		Customer t = new Customer();
//		t.setNid("abab");
//		t.setName("Ravi Teja");
//		t.setAddress("125 taft ave west haven ct");
//		t.setPnumber(1030183582l);
//		System.out.println(c.execute(t));
//	}
}