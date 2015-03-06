package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Tranasactions;
import connectionprovider.ConnectionProvider;

public class GetTranasactionsByTidCommand {
	public Tranasactions execute(int tid) {
		Tranasactions t = new Tranasactions();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM TRANASACTIONS WHERE tid = ?");
			stmt.setInt(1, tid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				t.setTid(rs.getInt("tid"));
				t.setNid(rs.getString("nid"));
				t.setType(rs.getString("type"));
				t.setDate(rs.getDate("date"));
				t.setJson(rs.getString("data"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
}
