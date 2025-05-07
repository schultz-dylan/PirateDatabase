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
		String password = "Caljam309!"; //TODO: set this to your password
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
	
	public void updatePirateNetWorth(long pid, double value) throws SQLException {
		String sql = "UPDATE Pirate SET NetWorth = NetWorth + ? WHERE Pid = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, pid);
		pstmt.setDouble(2, value);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void deleteTreasure(long tid) throws SQLException {
		String sql = "DELETE FROM Treasure WHERE Tid = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, tid);
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
	
	public ResultSet getIslandByLocation(String location, int limit) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Island.Name, sum(Treasure.Value), Island.Location "
				   + "FROM Island INNER JOIN Treasure ON Island.Iid = Treasure.Iid "
				   + "WHERE Island.Location LIKE ? "
				   + "GROUP BY Island.Iid "
				   + "ORDER BY sum(Treasure.Value) DESC "
				   + "LIMIT ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + location + "%");
		pstmt.setInt(2, limit);
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
	
	public ResultSet getPirateFullInfo(String fname, String mname, String lname, String alias) throws SQLException {
		ResultSet rs;
        String sql = "SELECT P.FirstName, P.MiddleName, P.LastName, P.Alias, Age, NetWorth, Role, C.Name, S.Name "
			 	   + "FROM Pirate AS P INNER JOIN Crew AS C INNER JOIN Ship AS S "
			 	   + "ON P.Cid = C.Cid AND P.Sid = S.Sid "
			 	   + "WHERE P.FirstName LIKE ? AND COALESCE(P.MiddleName, '') LIKE ? AND P.LastName LIKE ? AND COALESCE(P.Alias, '') LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + fname + "%");
		pstmt.setString(2, "%" + mname + "%");
		pstmt.setString(3, "%" + lname + "%");
		pstmt.setString(4, "%" + alias + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getPirateByNameAndCrew(String name, String crew) throws SQLException {
		ResultSet rs;
        String sql = "SELECT concat_ws(' ', FirstName, MiddleName, LastName) AS Name, Alias, Crew.Name, Pirate.Cid, Pid"
			 	   + "FROM Pirate INNER JOIN Crew ON Pirate.Cid = Crew.Cid "
			 	   + "WHERE Name LIKE ? AND Alias LIKE ? AND Crew.Name LIKE ? "
	 	   		   + "ORDER BY Crew.Name ASC, Ship.Name ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
		pstmt.setString(2, "%" + name + "%");
		pstmt.setString(3, "%" + crew + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getPirateAndCrewByNameAndCrew(String name, String crew) throws SQLException {
		ResultSet rs;
        String sql = "SELECT concat_ws(' ', FirstName, MiddleName, LastName) AS FullName, Alias, Crew.Name, Pid "
			 	   + "FROM Pirate INNER JOIN Crew ON Pirate.Cid = Crew.Cid "
			 	   + "WHERE concat_ws(' ', FirstName, MiddleName, LastName) LIKE ? AND Alias LIKE ? AND Crew.Name LIKE ? "
	 	   		   + "ORDER BY Crew.Name ASC";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
		pstmt.setString(2, "%" + name + "%");
		pstmt.setString(3, "%" + crew + "%");
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
	
	public ResultSet getShipByName(String name) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Name, Sid FROM Ship WHERE Name = ?";
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
	
	public ResultSet getTreasureByIsland(String name) throws SQLException {
		ResultSet rs;
        String sql = "SELECT Island.Name AS Island, Treasure.Location AS Location, Value "
   			 	   + "FROM Treasure INNER JOIN Island ON Treasure.Iid = Island.Iid "
   			 	   + "WHERE Island.Name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");
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
	
	/*
	 * Group 2 Query (HAVING)
	 */
	public ResultSet getCrewNetWorths(double amount) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Crew.Name, sum(Pirate.NetWorth) AS NetWorth "
				   + "FROM Pirate INNER JOIN Crew ON Pirate.Cid = Crew.Cid "
				   + "GROUP BY Crew.Cid "
				   + "HAVING NetWorth >= ? "
				   + "ORDER BY NetWorth";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setDouble(1, amount);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	/* 
	 * Group 2 Query (4 Tables Joined, HAVING)
	 */
	public ResultSet getPiratesInBattle(long bid) throws SQLException {
		ResultSet rs;
		String sql = "SELECT concat_ws(' ', FirstName, MiddleName, LastName) AS Name, Alias, Role, Ship.Name, Battle.Bid "
				   + "FROM Pirate INNER JOIN Ship INNER JOIN Participate INNER JOIN Battle "
				   + "ON Pirate.Sid = Ship.Sid AND Ship.Sid = Participate.Sid AND Participate.Bid = Battle.Bid "
				   + "HAVING Battle.Bid = ? "
				   + "ORDER BY Ship.Name, Name";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, bid);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	/*
	 * TODO
	 * experienced ships page
	 * update pirate page
	 * add location on the island table of create island page
	 * Make number entry spinners instead of text box
	 * error check so that no islands with the same location can have the same name
	 * comment
	 * eliminate unused imports
	 * make earlier pages made look cleaner
	 */
	
	/*
	 * Group 3 Query (Subquery)
	 */
	public ResultSet getRichestCrewIslands(String location) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Island.Name, Island.Location, max(Visit.Date), Island.Iid "
				+ "FROM Island INNER JOIN Visit INNER JOIN Ship INNER JOIN (SELECT Crew.Cid, sum(Pirate.NetWorth) AS NetWorth "
				+ "															FROM Pirate INNER JOIN Crew ON Pirate.Cid = Crew.Cid "
				+ "															GROUP BY Crew.Cid "
				+ "															HAVING NetWorth = max(NetWorth)) AS RichestCrew "
				+ "	ON Island.Iid = Visit.Iid AND Visit.Sid = Ship.Sid AND Ship.Cid = RichestCrew.Cid "
				+ " GROUP BY Island.Iid "
				+ " HAVING Island.Location LIKE ? ";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "%" + location + "%");
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getExperiencedShips(long cid) throws SQLException {
		ResultSet rs;
		String sql = "SELECT Ship.Name, count(Bid) "
				+ "	FROM Crew INNER JOIN Ship INNER JOIN Participate "
				+ "    ON Crew.Cid = Ship.Cid AND Ship.Sid = Participate.Sid "
				+ "    WHERE Crew.Cid = ? "
				+ "    GROUP BY Ship.Sid "
				+ "    HAVING count(Bid) > (SELECT avg(BattleCount.ct) "
				+ "							FROM (SELECT count(Bid) AS ct "
				+ "									FROM Participate "
				+ "									GROUP BY Sid) AS BattleCount)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, cid);
		rs = pstmt.executeQuery();
		return rs;
	}
}