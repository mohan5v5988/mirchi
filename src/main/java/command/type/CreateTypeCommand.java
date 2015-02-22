package command.type;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;
import model.Type;


public class CreateTypeCommand {
	public String execute(Type t) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO TYPE(type, rate) VALUES(?, ?) Returning type");
			stmt.setString(1, t.getType());
			stmt.setDouble(2, t.getRate());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("type");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	/*public static void main(String[] args) {
		CreateTypeCommand c = new CreateTypeCommand();
		Type t = new Type();
		t.setType("abcd");
		t.setRate(25.7);
		System.out.println(c.execute(t));
	}*/
}

