package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import connectionprovider.ConnectionProvider;

public class CreateCustomerCommand {
	public String execute(Customer c,String username,String password) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO CUSTOMER(nid, name, address, pnumber,email,dateofbirth) VALUES(?, ?, ?, ?, ?, ?) Returning nid");
			stmt.setString(1, c.getNid());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getAddress());
			stmt.setLong(4, c.getPnumber());
			stmt.setString(5, c.getEmail());
			stmt.setDate(6, java.sql.Date.valueOf(c.getDate()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				PreparedStatement stmt1 = connection
//						.prepareStatement("INSERT INTO USERNAME (nid, username, password) VALUES(?, ?, ?)");
//				stmt1.setString(1, rs.getString("nid"));
//				stmt1.setString(2, username);
//				stmt1.setString(3, password);
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
//		t.setNid("abcxaaa");
//		t.setName("Ravi Teja");
//		t.setAddress("125 taft ave west haven ct");
//		t.setPnumber(1030183582l);
//		t.setEmail("asdfg@gmail.com");
//		System.out.println(c.execute(t,"mmm","12345"));
//	}
}