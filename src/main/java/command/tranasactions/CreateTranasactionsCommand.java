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
//				System.out.println(rs.getString("tid"));
//				PreparedStatement stmt1 = connection
//						.prepareStatement("SELECT nid,date,data->>'totam' AS total FROM TRANASACTIONS WHERE tid=?");
//				stmt1.setInt(1, rs.getInt("tid"));
//				ResultSet rs1 = stmt1.executeQuery();
//				while (rs1.next()) { 
//					System.out.println(rs1.getString("total")); 
//					PreparedStatement stmt2 = connection
//							.prepareStatement("INSERT INTO "+rs1.getString("nid")+" (type,date,amount) VALUES (?,?,?)");
//					stmt2.setString(1, "b");
//					stmt2.setDate(2, rs1.getDate("date"));
//					stmt2.setDouble(3, rs1.getDouble("total"));
//					stmt2.execute();
//				}
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
