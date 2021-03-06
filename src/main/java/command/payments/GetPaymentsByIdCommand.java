package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Payments;
import connectionprovider.ConnectionProvider;

public class GetPaymentsByIdCommand {
	public Payments execute(int id) {
		Payments p = new Payments();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM PAYMENTS WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNid(rs.getString("nid"));
				p.setDate(df.format(rs.getDate("date")));
				p.setAmount(rs.getDouble("amount"));
				p.changeToPAmount();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
