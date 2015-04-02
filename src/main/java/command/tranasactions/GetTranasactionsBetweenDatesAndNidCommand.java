package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Tranasactions;
import connectionprovider.ConnectionProvider;

public class GetTranasactionsBetweenDatesAndNidCommand {
	public ArrayList<Tranasactions> execute(String date1,String date2,String nid) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		ArrayList<Tranasactions> tarr = new ArrayList<Tranasactions>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM TRANASACTIONS WHERE date BETWEEN ? AND ? AND nid=?");
			stmt.setDate(1, java.sql.Date.valueOf(date1));
			stmt.setDate(2, java.sql.Date.valueOf(date2));
			stmt.setString(3, nid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tranasactions t = new Tranasactions();
				t.setTid(rs.getInt("tid"));
				t.setNid(rs.getString("nid"));
				t.setType(rs.getString("type"));
				t.setDate(df.format(rs.getDate("date")));
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
