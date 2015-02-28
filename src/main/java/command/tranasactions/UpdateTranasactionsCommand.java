package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.postgresql.util.PGobject;

import model.Tranasactions;
import connectionprovider.ConnectionProvider;

public class UpdateTranasactionsCommand {
	public String execute(Tranasactions t) {
		try {
			PGobject pgObject = new PGobject();
			pgObject.setType("json");
			pgObject.setValue(t.getJson());
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE TRANASACTIONS SET nid=?, type=?, date=?, data=? WHERE tid=?");
			stmt.setString(1, t.getNid());
			stmt.setString(2, t.getType());
			stmt.setDate(3, t.getDate());
			stmt.setObject(4, pgObject);
			stmt.setInt(5, t.getTid());
			stmt.executeUpdate();
			return "1";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
}
