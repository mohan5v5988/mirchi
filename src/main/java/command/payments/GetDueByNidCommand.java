package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Due;
import connectionprovider.ConnectionProvider;

public class GetDueByNidCommand {
	public Due execute(String nid) {
		Due d = new Due();
		System.out.println(nid);
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM DUE WHERE nid = ?");
			stmt.setString(1, nid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				d.setNid(rs.getString("nid"));
				d.setAmount(rs.getDouble("tot"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}
}
