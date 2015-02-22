package command.type;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Type;
import connectionprovider.ConnectionProvider;

public class ListTypeCommand {
	public ArrayList<Type> execute() {
		ArrayList<Type> ret = new ArrayList<Type>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TYPE");
			while (rs.next()) {
				Type t = new Type();
				t.setType(rs.getString("type"));
				t.setRate(rs.getDouble("rate"));
				ret.add(t);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/*public static void main(String[] args) {
		ListTypeCommand c = new ListTypeCommand();
		ArrayList<Type> a = c.execute();
		for(Type b : a) {
			System.out.println(b);
		}
	}*/
}
