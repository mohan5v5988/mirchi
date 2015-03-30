package command.payments;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Due;
import connectionprovider.ConnectionProvider;

public class GetDueNid {
	public ArrayList<Due> execute() {
		ArrayList<Due> darr = new ArrayList<Due>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM DUE");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Due d = new Due();
				d.setNid(rs.getString("nid"));
				d.setAmount(rs.getDouble("tot"));
				darr.add(d);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return darr;
	}
//	public static void main(String[] args) {
//		GetDueNid d = new GetDueNid();
//		ArrayList<Due> a = d.execute();
//		for(Due z: a) {
//			System.out.println(z);
//		}
//	}
}
