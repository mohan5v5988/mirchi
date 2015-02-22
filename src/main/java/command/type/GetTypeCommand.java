package command.type;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;
import model.Type;



public class GetTypeCommand {
	public Type execute(String type) {
		Type t = new Type();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM TYPE WHERE type = ?");
			stmt.setString(1, type);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				t.setType(rs.getString("type"));
				t.setRate(rs.getDouble("rate"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	/*public static void main(String[] args) {
		GetTypeCommand c = new GetTypeCommand();
		System.out.println(c.execute("TEJA"));
	}*/
}