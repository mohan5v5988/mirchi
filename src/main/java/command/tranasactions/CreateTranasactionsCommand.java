package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PGobject;

import model.Tranasactions;
import connectionprovider.ConnectionProvider;

public class CreateTranasactionsCommand {
	public String execute(Tranasactions c) {
		try {
			PGobject pgObject = new PGobject();
			pgObject.setType("json");
			pgObject.setValue(c.getJson());
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO TRANASACTIONS(nid,type,date,data) VALUES (?,?,?,?) Returning tid");
			stmt.setString(1, c.getNid());
			stmt.setString(2, c.getType());
			stmt.setDate(3, c.getDate());
			stmt.setObject(4, pgObject);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("tid");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
}
