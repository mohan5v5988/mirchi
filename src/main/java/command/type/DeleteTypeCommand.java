package command.type;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;

public class DeleteTypeCommand {
    public String execute(String type) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement stmt = connection
            .prepareStatement("DELETE From TYPE WHERE type=?");
            stmt.setString(1, type);
            stmt.execute();
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return "-1";
    }
    /*public static void main(String[] args) {
    	DeleteTypeCommand c = new DeleteTypeCommand();
		c.execute("abcd");
		System.out.println("done");
	}*/
}