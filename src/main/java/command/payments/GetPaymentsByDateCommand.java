package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Payments;
import connectionprovider.ConnectionProvider;

public class GetPaymentsByDateCommand {
	public ArrayList<Payments> execute(String date) {
		ArrayList<Payments> parr = new ArrayList<Payments>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM PAYMENTS WHERE date = ?");
			stmt.setDate(1, java.sql.Date.valueOf(date));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Payments p = new Payments();
				p.setId(rs.getInt("id"));
				p.setNid(rs.getString("nid"));
				p.setDate(df.format(rs.getDate("date")));
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
