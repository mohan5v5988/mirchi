package command.tranasactions;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;

public class DeleteTranasactionsByTidCommand {
	public String execute(int tid) {
        try {
            Connection connection = ConnectionProvider.getConnection();
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
