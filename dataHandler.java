package jsp_azure_test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class DataHandler {
	
	private Connection conn;
	// Azure SQL connection credentials
	private String server = "password-sql-server.database.windows.net";
	private String database = "databasename-sql-db";
	private String username = "username";
	private String password = "password";
	// Resulting connection string
	final private String url =String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
			+ "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
			server, database, username, password);
	
	// Initialize and save the database connection
	private void getDBConnection() throws SQLException {
		if (conn != null) {
			return;
		}
		this.conn = DriverManager.getConnection(url);
	}
	
	
	// Return the result of selecting everything from the customer table
	public ResultSet getAllCustomers() throws SQLException {
		getDBConnection();
		final String sqlQuery = "SELECT * FROM customer;";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		return stmt.executeQuery();
	}
	
	
	
	public ResultSet getCustomers(int l, int u) throws SQLException {
		getDBConnection();
		final String sqlQuery = "SELECT * FROM customer WHERE category BETWEEN "+l+" AND "+u+" ORDER BY name;";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		return stmt.executeQuery();
	}

	
	// Inserting a record into the customer table with the given attribute values
	public boolean addCustomer(String name, String address, int category) throws SQLException {
		getDBConnection(); // Prepare the database connection
		// Preparing the SQL statement
		final String sqlQuery =	"INSERT INTO customer" + "(name, address, category) " +	"VALUES " +	"(?, ?, ?)";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		// Replacing the '?' in the above statement with the input attribute values
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setInt(3, category);
		// Executing the query, if only one record is updated, indicating success by returning true
		return stmt.executeUpdate() == 1;
	}	
}
