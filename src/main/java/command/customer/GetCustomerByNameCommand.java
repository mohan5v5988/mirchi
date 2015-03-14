package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import connectionprovider.ConnectionProvider;

public class GetCustomerByNameCommand {
	public Customer execute(String name) {
		Customer t = new Customer();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM CUSTOMER WHERE name = ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				t.setNid(rs.getString("nid"));
				t.setName(rs.getString("name"));
				t.setAddress(rs.getString("address"));
				t.setPnumber(rs.getInt("pnumber"));
				t.setEmail(rs.getString("email"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	/*public static void main(String[] args) {
		GetCustomerByNameCommand c = new GetCustomerByNameCommand();
		System.out.println(c.execute("Ravi"));
	}*/
}
