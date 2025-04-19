package Database;
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
	
	public void insertCrew(String name, String location) throws SQLException {
		String sql = "INSERT INTO Crew(Name, Location) VALUES(?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, location);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void insertShip(String name, String type, long cid) throws SQLException {
		String sql = "INSERT INTO Ship(Name, Type, Cid) VALUES(?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, type);
		pstmt.setLong(3, cid);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void insertPirate(String fName, String mName, String lName, String age, String alias, String netWorth, String role, long cid, long sid) throws SQLException {
		String sql = "INSERT INTO Pirate(FirstName, MiddleName, LastName, Alias, Age, NetWorth, Role, Cid, Sid) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, fName);
		pstmt.setString(2, mName);
		pstmt.setString(3, lName);
		pstmt.setString(4, alias);
		pstmt.setString(5, age);
		pstmt.setString(6, netWorth);
		pstmt.setString(7, role);
		pstmt.setLong(8, cid);
		pstmt.setLong(9, sid);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public ResultSet getCrewByLikeName(String name) throws SQLException {
		ResultSet rs;
        String sql = "SELECT Name, Cid "
   			 	   + "FROM Crew "
   			 	   + "WHERE Name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getCrewByName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT * FROM Crew WHERE Name = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getShipByName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT * FROM Ship WHERE Name = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getShipByNameAndCrew(String shipName, String crewName) throws SQLException {
		ResultSet rs;
        String sql = "SELECT Ship.Name, Crew.Name, Ship.Sid, Ship.Cid "
   			 	   + "FROM Ship INNER JOIN Crew ON Ship.Cid = Crew.Cid "
   			 	   + "WHERE Ship.Name LIKE ? AND Crew.Name LIKE ?"
	 	   		   + "ORDER BY Crew.Name ASC, Ship.Name ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + shipName + "%");
		pstmt.setString(2, "%" + crewName + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getPirateByName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT * FROM Pirate WHERE Name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getCrewNetWorths(double amount) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Crew.Name AS CrewName, sum(Pirate.NetWorth) AS NetWorth "
				   + "FROM Pirate INNER JOIN Crew ON Cid "
				   + "GROUP BY CrewName "
				   + "HAVING NetWorth >= ? "
				   + "ORDER BY NetWorth";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setDouble(1, amount);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getIslandCandidates(double amount) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Crew.Name AS CrewName, sum(Pirate.NetWorth) AS NetWorth "
				   + "FROM Pirate INNER JOIN Crew ON Cid "
				   + "GROUP BY CrewName "
				   + "HAVING NetWorth >= ? "
				   + "ORDER BY NetWorth";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setDouble(1, amount);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getRichestCrewIslands(String location) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Crew.Name AS CrewName, sum(Pirate.NetWorth) AS NetWorth "
				   + "FROM Pirate INNER JOIN Crew ON Cid "
				   + "GROUP BY CrewName "
				   + "ORDER BY NetWorth";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, location);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	
}