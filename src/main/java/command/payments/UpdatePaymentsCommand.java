package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Payments;
import connectionprovider.ConnectionProvider;

public class UpdatePaymentsCommand {
	public String execute(Payments p) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE PAYMENTS SET nid=?,date=?,amount=? WHERE id=?");
			stmt.setString(1, p.getNid());
			stmt.setDate(2, p.getDate());
			stmt.setDouble(3, p.getAmount());
			stmt.setInt(4, p.getId());
			stmt.executeUpdate();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
}
