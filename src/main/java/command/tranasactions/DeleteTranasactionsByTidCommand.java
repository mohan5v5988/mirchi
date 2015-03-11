package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import connectionprovider.ConnectionProvider;

public class DeleteTranasactionsByTidCommand {
	public String execute(int tid) {
        try {
            Connection connection = ConnectionProvider.getConnection();
//            PreparedStatement stmt1 = connection
//                    .prepareStatement("SELECT nid,date,data->>'totam' AS total From TRANASACTIONS WHERE tid=?");
//            stmt1.setInt(1, tid);
//            ResultSet rs = stmt1.executeQuery();
//            while(rs.next()) {
//            	PreparedStatement stmt2 = connection
//                        .prepareStatement("DELETE From "+rs.getString("nid")+" WHERE date = ? AND amount = ?");
//            	stmt2.setDate(1, rs.getDate("date"));
//            	stmt2.setDouble(2, rs.getDouble("total"));
//            	stmt2.execute();
//            }
            PreparedStatement stmt = connection
            .prepareStatement("DELETE From TRANASACTIONS WHERE tid=?");
            stmt.setInt(1, tid);
            stmt.execute();
            return "1";
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return "-1";
    }
}
