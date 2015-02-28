package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tranasactions;
import connectionprovider.ConnectionProvider;

public class GetTranasactionsByDateCommand {
	public ArrayList<Tranasactions> execute(String date) {
		ArrayList<Tranasactions> tarr = new ArrayList<Tranasactions>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM TRANASACTIONS WHERE date = ?");
			stmt.setDate(1, java.sql.Date.valueOf(date));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tranasactions t = new Tranasactions();
				t.setTid(rs.getInt("tid"));
				t.setNid(rs.getString("nid"));
				t.setType(rs.getString("type"));
				t.setDate(rs.getDate("date"));
				t.setJson(rs.getString("data"));
				tarr.add(t);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tarr;
	}
}
