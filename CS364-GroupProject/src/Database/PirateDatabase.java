package Database;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PirateDatabase {

	/*
	 * load SQL driver (JDBC: Java Database Connector/ODBC)
	 * - add to build path
	 * 
	 * set up our database (script)
	 * 
	 * connect to the database
	 * 
	 * insert/modify/delete data (Java)
	 * 
	 * query data (Java)
	 * 
	 * disconnect from the database
	 * 
	 */

	String url = "jdbc:mysql://localhost:3306/piratedb?user=root&password=";


	
	private Connection connection;
	
	public PirateDatabase() {
		String password = ""; //TODO: set this to your password
		url = url + password;
	}
	
	public void connect() {							//Connect to database when given url
		try {
			connection = DriverManager.getConnection(url);
			System.out.println("Connection Successful");
		} catch (SQLException e) {
			System.out.println("Cannot connect!");
			System.out.println(e);
		}
	}
	
	public void disconnect() {					//disconnect from database
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Cannot disconnect!");
		}
	}
	
	public ResultSet runQuery(String query) throws SQLException {		//Runs a query in the form of a string and returns a result set
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public int runUpdate(String query) throws SQLException {
	    // Ensure that a connection exists
	    if (connection == null) {
	        throw new SQLException("No database connection established.");
	    }
	    
	    Statement stmt = null;
	    int rowsAffected = 0;
	    try {
	        stmt = connection.createStatement();
	        rowsAffected = stmt.executeUpdate(query);
	    } finally {
	        // Ensure the Statement is closed even if an exception occurs.
	        if (stmt != null) {
	            stmt.close();
	        }
	    }
	    return rowsAffected;
	}
