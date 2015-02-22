package command.customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;

public class DeleteCustomerCommand {
	public String execute(String nid) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement stmt = connection
            .prepareStatement("DELETE From CUSTOMER WHERE nid=?");
            stmt.setString(1, nid);
            stmt.execute();
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return "-1";
    }
//    public static void main(String[] args) {
//    	DeleteCustomerCommand c = new DeleteCustomerCommand();
//		c.execute("abcd");
//		System.out.println("done");
//	}
}
