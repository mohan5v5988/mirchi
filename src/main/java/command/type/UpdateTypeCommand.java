package command.type;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Type;

import connectionprovider.ConnectionProvider;

public class UpdateTypeCommand {
	public String execute(Type t) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE TYPE SET rate=? WHERE type=?");
			stmt.setDouble(1, t.getRate());
			stmt.setString(2, t.getType());
			stmt.executeUpdate();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	/*public static void main(String[] args) {
		UpdateTypeCommand c = new UpdateTypeCommand();
		Type t = new Type();
		t.setType("TEJA");
		t.setRate(54.5);
		c.execute(t);
	}*/
}
