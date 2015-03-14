package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Payments;
import connectionprovider.ConnectionProvider;

public class GetPaymentsByNidCommand {
	public ArrayList<Payments> execute(String nid) {
		ArrayList<Payments> parr = new ArrayList<Payments>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM PAYMENTS WHERE nid = ?");
			stmt.setString(1, nid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Payments p = new Payments();
				p.setId(rs.getInt("id"));
				p.setNid(rs.getString("nid"));
				p.setDate(rs.getDate("date"));
				p.setAmount(rs.getDouble("amount"));
				p.changeToPAmount();
				parr.add(p);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parr;
	}
}
