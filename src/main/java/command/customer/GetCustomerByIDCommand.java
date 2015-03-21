package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Customer;
import connectionprovider.ConnectionProvider;

public class GetCustomerByIDCommand {
	public Customer execute(String nid) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Customer t = new Customer();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM CUSTOMER WHERE nid = ?");
			stmt.setString(1, nid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				t.setNid(rs.getString("nid"));
				t.setName(rs.getString("name"));
				t.setAddress(rs.getString("address"));
				t.setPnumber(rs.getInt("pnumber"));
				t.setEmail(rs.getString("email"));
				t.setDate(df.format(rs.getDate("dateofbirth")));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
//	public static void main(String[] args) {
//		GetCustomerByIDCommand c = new GetCustomerByIDCommand();
//		System.out.println(c.execute("mv"));
//	}
}
