package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Payments;
import connectionprovider.ConnectionProvider;

public class CreatePaymentCommand {
	public String execute(Payments p) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO PAYMENTS(nid, date, amount) VALUES(?, ?, ?) Returning id");
			stmt.setString(1, p.getNid());
			stmt.setDate(2, java.sql.Date.valueOf(p.getDate()));
			stmt.setDouble(3, p.dbAmount());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("id");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	public static void main(String[] args) {
		CreatePaymentCommand c = new CreatePaymentCommand();
		Payments p = new Payments();
		p.setId(0);
		p.setNid("mv");
		p.setAmount(500.0);
	}
}