package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import connectionprovider.ConnectionProvider;

public class ListCustomerCommand {
	public ArrayList<Customer> execute() {
		ArrayList<Customer> ret = new ArrayList<Customer>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
			while (rs.next()) {
				Customer t = new Customer();
				t.setNid(rs.getString("nid"));
				t.setName(rs.getString("name"));
				t.setAddress(rs.getString("address"));
				t.setPnumber(rs.getLong("pnumber"));
				t.setEmail(rs.getString("email"));
				ret.add(t);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static void main(String[] args) {
		ListCustomerCommand c = new ListCustomerCommand();
		ArrayList<Customer> a = c.execute();
		for(Customer b : a) {
			System.out.println(b);
		}
	}
}
