package DVA.Project.DataIngest.Java.DataIngest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import DVA.Project.DataIngest.Java.DataIngest.model.Root;
import DVA.Project.DataIngest.Java.DataIngest.model.RootDivisions;
import DVA.Project.DataIngest.Java.DataIngest.model.RootPeople;
import DVA.Project.DataIngest.Java.DataIngest.model.RootTeamStats;
import DVA.Project.DataIngest.Java.DataIngest.model.generic.RootTeamStatsGeneric;
import junit.framework.TestCase;

/**
 * Unit test method to run on demand and pull data...
 * 
 */
public class ConsumeAPITest extends TestCase {
	static String globalURL = "";

	static final String URL = "jdbc:mysql://dva-spring-2020db.ck1ll6cmjbhr.us-east-2.rds.amazonaws.com";
	static final String DATABASE_NAME = "dva_database_test";
	static final String USERNAME = "admin";
	static final String PASSWORD = "spring20201983";

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static Logger logger = Logger.getLogger(ConsumeAPITest.class);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// PULLING DATA FROM API
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//// https://statsapi.web.nhl.com/api/v1/teams
	public static Root getAllTeams() {
		logger.debug("============= GET ALL TEAMS ==================");
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/teams";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

		logger.debug(response.getBody());

		Gson g = new Gson();
		Root root = g.fromJson(response.getBody().toString(), Root.class);
		logger.debug("=================================================");
		return root;
	}

	// https://statsapi.web.nhl.com/api/v1/teams/1
	public void getTeamById() {
		logger.debug("============= GET TEAM BY ID ==================");
		String teamId = "1";
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/teams/" + teamId;
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		logger.debug(response.getBody());

		Gson g = new Gson();
		Root root = g.fromJson(response.getBody().toString(), Root.class);
		logger.debug("=================================================");
	}

	// https://statsapi.web.nhl.com/api/v1/teams/1?expand=team.roster
	// ?expand=team.roster Shows roster of active players for the specified team
	public static Root getRosterByTeam(int teamId) {
		logger.debug("============= GET ROSTER BY TEAM ID ==================");
		// String teamId = "1";
		String id = String.valueOf(teamId);
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/teams/" + id + "?expand=team.roster";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		logger.debug(response.getBody());

		Gson g = new Gson();
		Root root = g.fromJson(response.getBody().toString(), Root.class);
		logger.debug("=================================================");
		return root;
	}

	// https://statsapi.web.nhl.com/api/v1/divisions
	// GET https://statsapi.web.nhl.com/api/v1/divisions Returns full list of
	// divisions
	// and associated data like which conference they belong to, id values and API
	// links. Does not show inactive divisions
	public void getAllDivisions() {
		logger.debug("============= GET ALL DIVISIONS ==================");
		String teamId = "1";
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/divisions";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		logger.debug(response.getBody());

		Gson g = new Gson();
		RootDivisions root = g.fromJson(response.getBody().toString(), RootDivisions.class);
		logger.debug("=================================================");
	}

	// GET https://statsapi.web.nhl.com/api/v1/people/ID Gets details for a player,
	// must specify the id value in order to return data.
	public static RootPeople getPeopleById(int playerId) {
		logger.debug("============= GET PLAYER BY ID ==================");
		// String playerId = "8477474";
		String id = String.valueOf(playerId);
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/people/" + id;
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		logger.debug(response.getBody());

		Gson g = new Gson();
		RootPeople root = g.fromJson(response.getBody().toString(), RootPeople.class);
		logger.debug("=================================================");
		return root;
	}

	// GET https://statsapi.web.nhl.com/api/v1/people/ID Gets details for a player,
	// must specify the id value in order to return data.
	public void getAllPeople() { // PLAYERS
		logger.debug("============= GET PLAYER BY ID ==================");

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/people";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		logger.debug(response.getBody());

		Gson g = new Gson();
		RootPeople root = g.fromJson(response.getBody().toString(), RootPeople.class);
		logger.debug("=================================================");
	}

	// GET
	// https://statsapi.web.nhl.com/api/v1/teams/1?expand=team.stats&season=20142015,
	// must specify the team id and season value in order to return data.
	public static Root getStatsByTeamIdBySeason(int teamId, String season) { // PLAYERS
		try {
			logger.debug("============= GET PLAYER BY ID ==================");

			RestTemplate restTemplate = new RestTemplate();
			String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/teams/" + teamId + "?expand=team.stats&season="
					+ season;
			ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
			logger.debug(response.getBody());

			Gson g = new Gson();
			Root root = g.fromJson(response.getBody().toString(), Root.class);
			logger.debug("=================================================");
			return root;
		} catch (Exception ex) {
			// if landed here, most likely the team id didnt have season information
			return null;
		}
	}

	// GET https://statsapi.web.nhl.com/api/v1/game/2018020001/boxscore,
	// must specify the team id and season value in order to return data.
	public static RootTeamStats getTeamStatsPerGame(String season, String gameId) { 
	//public static RootTeamStats getTeamStatsPerGame() {
		try {
			logger.debug("========== GET TEAM STATS PER GAME ===============");

			RestTemplate restTemplate = new RestTemplate();
			String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/game/"+season+"02"+gameId+"/boxscore";
			//String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/game/2016020003/boxscore";
			globalURL = fooResourceUrl;
			logger.debug("Pulling Data from endpoint --> " + fooResourceUrl);
			ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
			//logger.debug(response.getBody());

			Gson g = new Gson();
			RootTeamStats root = g.fromJson(response.getBody().toString(), RootTeamStats.class);
			logger.debug("=================================================");
			return root;
		} catch (Exception ex) {
			// if landed here, most likely the team id didnt have season information
			logger.error("oppsss...something bad happened...");
			logger.error("ERROR--> " + globalURL);
			logger.error("=================================================");
			return null;
		}
	}
	
	
		// GET https://statsapi.web.nhl.com/api/v1/game/2018020001/boxscore,
		// must specify the team id and season value in order to return data.
		public static String getPlayerStatsPerGame(String season, String gameId) { 
		//public static RootTeamStats getTeamStatsPerGame() {
			try {
				logger.debug("========== GET TEAM STATS PER GAME ===============");

				RestTemplate restTemplate = new RestTemplate();
				String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/game/"+season+"02"+gameId+"/boxscore";
				//String fooResourceUrl = "https://statsapi.web.nhl.com/api/v1/game/2016020003/boxscore";
				globalURL = fooResourceUrl;
				logger.debug("Pulling Data from endpoint --> " + fooResourceUrl);
				ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
				//logger.debug(response.getBody());

				Gson g = new Gson();
				RootTeamStatsGeneric root = g.fromJson(response.getBody().toString(), RootTeamStatsGeneric.class);
				logger.debug("=================================================");
				//return root;
				return response.getBody().toString();
			} catch (Exception ex) {
				// if landed here, most likely the team id didnt have season information
				logger.error("oppsss...something bad happened...");
				logger.error("ERROR--> " + globalURL);
				logger.error("=================================================");
				return null;
			}
		}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// SQL STATEMENTS
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void testConnection() {

		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	// does an insert , then deletes content
	public void insertDataTest() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager
					.getConnection(URL + "/" + DATABASE_NAME + "?" + "user=" + USERNAME + "&password=" + PASSWORD);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".comments");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect.prepareStatement(
					"insert into  " + DATABASE_NAME + ".comments values (default, ?, ?, ?, ? , ?, ?)");

			// Parameters start with 1
			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "Test2");
			preparedStatement.setString(3, "Test3");
			preparedStatement.setDate(4, new java.sql.Date(2020, 01, 01));
			preparedStatement.setString(5, "Test4");
			preparedStatement.setString(6, "Test5");
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement(
					"SELECT myuser, webpage, datum, summary, COMMENTS from " + DATABASE_NAME + ".comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect
					.prepareStatement("delete from " + DATABASE_NAME + ".comments where myuser= ? ; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();

			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".comments");
			writeMetaData(resultSet);

		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
