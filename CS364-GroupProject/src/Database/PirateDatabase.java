package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class PirateDatabase {

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
	
	public void insertBattle(String name, String location, int casualties, String winner, double lootAmount, LocalDate date) throws SQLException {
		String sql = "INSERT INTO Battle(Name, Location, Casualties, Winner, LootAmount, Date) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, location);
		pstmt.setInt(3, casualties);
		pstmt.setString(4, winner);
		pstmt.setDouble(5, lootAmount);
		pstmt.setDate(6, java.sql.Date.valueOf(date));
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void insertParticipate(long bid, long sid, boolean wasSunk) throws SQLException {
		String sql = "INSERT INTO Participate(Bid, Sid, WasSunk) VALUES(?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, bid);
		pstmt.setLong(2, sid);
		pstmt.setBoolean(3, wasSunk);
		pstmt.executeUpdate();
		pstmt.close();
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
	
	public void insertIsland(String name, String location) throws SQLException {
		String sql = "INSERT INTO Island(Name, Location) VALUES(?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, location);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void insertTreasure(double value, String location, long iid, long pid) throws SQLException {
		String sql = "INSERT INTO Treasure(Value, Location, Iid, Pid) VALUES(?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setDouble(1, value);
		pstmt.setString(2, location);
		pstmt.setLong(3, iid);
		pstmt.setLong(4, pid);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void insertVisit(long iid, long sid, LocalDate date) throws SQLException {
		String sql = "INSERT INTO Visit(Iid, Sid, Date) VALUES(?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, iid);
		pstmt.setLong(2, sid);
		pstmt.setDate(3, java.sql.Date.valueOf(date));
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
	
	public ResultSet getBattleByLikeName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Name, Winner, Location, Date, Bid FROM Battle WHERE Name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getIslandByLikeName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Name, Iid FROM Island WHERE Name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getParticipateByIDs(long bid, long sid) throws SQLException {
		ResultSet rs;
		String sql = "SELECT * FROM Participate WHERE Bid = ? AND Sid = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, bid);
		pstmt.setLong(2, sid);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getShipAndCrewByShipName(String name) throws SQLException {
		ResultSet rs;
        String sql = "SELECT Ship.Name, Crew.Name, Ship.Sid "
			 	   + "FROM Ship INNER JOIN Crew ON Ship.Cid = Crew.Cid "
			 	   + "WHERE Ship.Name LIKE ? "
	 	   		   + "ORDER BY Crew.Name ASC, Ship.Name ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getShipByLikeName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Name, Sid FROM Ship WHERE Name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
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
	
	public ResultSet getVisitByDateAndID(long iid, long sid, LocalDate date) throws SQLException {
		ResultSet rs;
        String sql = "SELECT * FROM Visit "
   			 	   + "WHERE Iid = ? AND Sid = ? AND Date = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, iid);
		pstmt.setLong(2, sid);
		pstmt.setDate(3, java.sql.Date.valueOf(date));
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getPirateByNameAndCrew(String fName, String lName, String alias, String crewName) throws SQLException {
		ResultSet rs;
		String sql = "SELECT FirstName, MiddleName, LastName, Alias, Crew.Name, Pid "
				   + "FROM Pirate INNER JOIN Crew ON Pirate.Cid = Crew.Cid "
				   + "WHERE FirstName LIKE ? AND LastName LIKE ? AND Alias LIKE ? AND Crew.Name LIKE ? "
				   + "ORDER BY Crew.Name ASC, FirstName ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + fName + "%");
		pstmt.setString(2, "%" + lName + "%");
		pstmt.setString(3, "%" + alias + "%");
		pstmt.setString(4, "%" + crewName + "%");
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