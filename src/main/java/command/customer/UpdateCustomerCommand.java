package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Customer;
import connectionprovider.ConnectionProvider;

public class UpdateCustomerCommand {
	public String execute(Customer t) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE CUSTOMER SET name=?, address=?, pnumber=? WHERE nid=?");
			stmt.setString(1, t.getName());
			stmt.setString(2, t.getAddress());
			stmt.setDouble(3, t.getPnumber());
			stmt.setString(4, t.getNid());
			stmt.executeUpdate();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	/*public static void main(String[] args) {
		UpdateCustomerCommand c = new UpdateCustomerCommand();
		Customer t = new Customer();
		t.setNid("abcd");
		t.setName("Ravi");
		t.setAddress("125 taft ave west haven ct");
		t.setPnumber(1030183582l);
		c.execute(t);
	}*/
}
