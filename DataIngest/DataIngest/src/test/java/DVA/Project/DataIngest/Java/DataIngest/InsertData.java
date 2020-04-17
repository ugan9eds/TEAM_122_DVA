package DVA.Project.DataIngest.Java.DataIngest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;

import DVA.Project.DataIngest.Java.DataIngest.model.People;
import DVA.Project.DataIngest.Java.DataIngest.model.Root;
import DVA.Project.DataIngest.Java.DataIngest.model.RootPeople;
import DVA.Project.DataIngest.Java.DataIngest.model.RootTeamStats;
import DVA.Project.DataIngest.Java.DataIngest.model.Rosterunit;
import DVA.Project.DataIngest.Java.DataIngest.model.Stat;
import DVA.Project.DataIngest.Java.DataIngest.model.TeamSkaterStats;
import DVA.Project.DataIngest.Java.DataIngest.model.Teams;
import DVA.Project.DataIngest.Java.DataIngest.model.generic.PlayerDynamic;
import DVA.Project.DataIngest.Java.DataIngest.model.generic.RootTeamStatsGeneric;
import junit.framework.TestCase;

/**
 * Just run 1 method at a time to start insertion of data
 * 
 * @author cfebrero
 *
 */
public class InsertData extends TestCase {

	static final String URL = "jdbc:mysql://dva-spring-2020db.ck1ll6cmjbhr.us-east-2.rds.amazonaws.com";
	static final String DATABASE_NAME = "dva_2020_nhl";
	static final String USERNAME = "admin";
	static final String PASSWORD = "spring20201983";

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static Logger logger = Logger.getLogger(InsertData.class);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////// INSERT ALL TEAMS ////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertAllTeams() {
		try {

			// Get the TEAMS Data from API
			// uncomment method call to run....
			Root teamsList = null;// ConsumeAPITest.getAllTeams();

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager
					.getConnection(URL + "/" + DATABASE_NAME + "?" + "user=" + USERNAME + "&password=" + PASSWORD);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			for (Teams team : teamsList.getTeams()) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME
						+ ".teams values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

				// Parameters start with 1
				preparedStatement.setInt(1, team.getId());
				preparedStatement.setString(2, team.getName());
				preparedStatement.setString(3, team.getLink());
				preparedStatement.setString(4, team.getVenue().getName());
				preparedStatement.setString(5, team.getVenue().getLink());
				preparedStatement.setString(6, team.getVenue().getCity());
				preparedStatement.setString(7, team.getVenue().getTimeZone().getId());
				preparedStatement.setInt(8, team.getVenue().getTimeZone().getOffset());
				preparedStatement.setString(9, team.getVenue().getTimeZone().getTz());
				preparedStatement.setString(10, team.getAbbreviation());
				preparedStatement.setString(11, team.getName());
				preparedStatement.setString(12, team.getLocationName());
				preparedStatement.setString(13, team.getFirstYearOfPlay());
				preparedStatement.setInt(14, team.getDivision().getId());
				preparedStatement.setString(15, team.getDivision().getName());
				preparedStatement.setString(16, team.getDivision().getLink());
				preparedStatement.setInt(17, team.getConference().getId());
				preparedStatement.setString(18, team.getConference().getName());
				preparedStatement.setString(19, team.getConference().getLink());
				preparedStatement.setInt(20, team.getFranchise().getFranchiseId());
				preparedStatement.setString(21, team.getFranchise().getTeamName());
				preparedStatement.setString(22, team.getFranchise().getLink());
				preparedStatement.setString(23, team.getShortName());
				preparedStatement.setString(24, team.getOfficialSiteUrl());
				preparedStatement.setInt(25, team.getFranchiseId());
				preparedStatement.setString(26, team.getActive());
				preparedStatement.executeUpdate();
			}

			System.out.println("=================== DONE ===================");

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

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////// INSERT ALL PLAYERS
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertAllPlayers() {
		try {

			// Get the TEAMS Data from API
			// uncomment method call to run....
			Root teamsList = ConsumeAPITest.getAllTeams();
			List<Integer> playerIdList = new ArrayList<>();
			for (Teams team : teamsList.getTeams()) {
				Root roster = ConsumeAPITest.getRosterByTeam(team.getId());
				for (Teams rosterTeam : roster.getTeams()) {
					for (Rosterunit innerRoster : rosterTeam.getRoster().getRoster()) {
						playerIdList.add(innerRoster.getPerson().getId());
					}
				}
			}

			List<RootPeople> peopleList = new ArrayList<>();
			for (Integer peopleId : playerIdList) {
				peopleList.add(ConsumeAPITest.getPeopleById(peopleId));
			}

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager
					.getConnection(URL + "/" + DATABASE_NAME + "?" + "user=" + USERNAME + "&password=" + PASSWORD);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			for (RootPeople rootPeople : peopleList) {
				People people = rootPeople.getPeople().get(0); // list of 1

				// PreparedStatements can use variables and are more efficient
				preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME
						+ ".players values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

				// Parameters start with 1
				preparedStatement.setInt(1, people.getId());
				preparedStatement.setString(2, people.getFullName());
				preparedStatement.setString(3, people.getLink());
				preparedStatement.setString(4, people.getFirstName());
				preparedStatement.setString(5, people.getLastName());
				preparedStatement.setString(6, people.getPrimaryNumber());
				preparedStatement.setString(7, people.getBirthDate());
				preparedStatement.setInt(8, people.getCurrentAge());
				preparedStatement.setString(9, people.getBirthCity());
				preparedStatement.setString(10, people.getBirthStateProvince());
				preparedStatement.setString(11, people.getBirthCountry());
				preparedStatement.setString(12, people.getNationality());
				preparedStatement.setString(13, people.getHeight());
				preparedStatement.setInt(14, people.getWeight());
				preparedStatement.setString(15, people.getActive());
				preparedStatement.setString(16, people.getAlternateCaptain());
				preparedStatement.setString(17, people.getCaptain());
				preparedStatement.setString(18, people.getRookie());
				preparedStatement.setString(19, people.getShootsCatches());
				preparedStatement.setString(20, people.getRosterStatus());
				preparedStatement.setInt(21, people.getCurrentTeam().getId());
				preparedStatement.setString(22, people.getCurrentTeam().getName());
				preparedStatement.setString(23, people.getCurrentTeam().getLink());
				preparedStatement.setString(24, people.getPrimaryPosition().getCode());
				preparedStatement.setString(25, people.getPrimaryPosition().getName());
				preparedStatement.setString(26, people.getPrimaryPosition().getType());
				preparedStatement.setString(27, people.getPrimaryPosition().getAbbreviation());
				preparedStatement.executeUpdate();
			}

			System.out.println("=================== DONE INSERTING PLAYERS ===================");
			close();
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////// INSERT STATS PER TEAM PER SEASON ///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertByTeamIdAndSeason() {
		try {

			// Get the TEAMS Data from API
			// uncomment method call to run....

			// We need just the last 5 seasons
			List<String> seasonList = new ArrayList<>();
			seasonList.add("20152016");
			seasonList.add("20162017");
			seasonList.add("20172018");
			seasonList.add("20182019");
			seasonList.add("20192020");

			Root teamsList = ConsumeAPITest.getAllTeams();

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager
					.getConnection(URL + "/" + DATABASE_NAME + "?" + "user=" + USERNAME + "&password=" + PASSWORD);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			for (Teams team : teamsList.getTeams()) {
				for (String season : seasonList) {
					Root statsPerTeamPerSeason = ConsumeAPITest.getStatsByTeamIdBySeason(team.getId(), season);
					if (statsPerTeamPerSeason == null) {
						continue;
					}
					Stat stat = statsPerTeamPerSeason.getTeams().get(0).getTeamStats().get(0).getSplits().get(0)
							.getStat();

					// PreparedStatements can use variables and are more efficient
					preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME
							+ ".season_team_stats values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					// Parameters start with 1
					preparedStatement.setInt(1, stat.getGamesPlayed());
					preparedStatement.setInt(2, fromStringToInt(stat.getWins()));
					preparedStatement.setInt(3, fromStringToInt(stat.getLosses()));
					preparedStatement.setInt(4, fromStringToInt(stat.getOt()));
					preparedStatement.setInt(5, fromStringToInt(stat.getPts()));
					preparedStatement.setFloat(6, fromStringToFloat(stat.getPtPctg()));
					preparedStatement.setFloat(7, fromStringToFloat(stat.getGoalsPerGame()));
					preparedStatement.setFloat(8, fromStringToFloat(stat.getGoalsAgainstPerGame()));
					preparedStatement.setFloat(9, fromStringToFloat(stat.getEvGGARatio()));
					preparedStatement.setFloat(10, fromStringToFloat(stat.getPowerPlayPercentage()));
					preparedStatement.setFloat(11, fromStringToFloat(stat.getPowerPlayGoals()));
					preparedStatement.setFloat(12, fromStringToFloat(stat.getPowerPlayGoalsAgainst()));
					preparedStatement.setFloat(13, fromStringToFloat(stat.getPowerPlayOpportunities()));
					preparedStatement.setFloat(14, fromStringToFloat(stat.getPenaltyKillPercentage()));
					preparedStatement.setFloat(15, fromStringToFloat(stat.getShotsPerGame()));
					preparedStatement.setFloat(16, fromStringToFloat(stat.getShotsAllowed()));
					preparedStatement.setFloat(17, fromStringToFloat(stat.getWinScoreFirst()));
					preparedStatement.setFloat(18, fromStringToFloat(stat.getWinOppScoreFirst()));
					preparedStatement.setFloat(19, fromStringToFloat(stat.getWinLeadFirstPer()));
					preparedStatement.setFloat(20, fromStringToFloat(stat.getWinLeadSecondPer()));
					preparedStatement.setFloat(21, fromStringToFloat(stat.getWinOutshootOpp()));
					preparedStatement.setFloat(22, fromStringToFloat(stat.getWinOutshotByOpp()));
					preparedStatement.setFloat(23, fromStringToFloat(stat.getFaceOffsTaken()));
					preparedStatement.setFloat(24, fromStringToFloat(stat.getFaceOffsWon()));
					preparedStatement.setFloat(25, fromStringToFloat(stat.getFaceOffsLost()));
					preparedStatement.setFloat(26, fromStringToFloat(stat.getFaceOffWinPercentage()));
					preparedStatement.setFloat(27, fromStringToFloat(stat.getShootingPctg()));
					preparedStatement.setFloat(28, fromStringToFloat(stat.getSavePctg()));

					preparedStatement.setInt(29, team.getId());
					preparedStatement.setString(30, team.getName());
					preparedStatement.setString(31, team.getLink());
					preparedStatement.setString(32, season);
					preparedStatement.executeUpdate();
				}
			}

			System.out.println("=============== DONE INSERTING STATS PER TEAM PER SEASSON ===============");
			close();
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			close();
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Insert team start per game Id for regular season
	// https://statsapi.web.nhl.com/api/v1/game/2018020001/boxscore
	public void insertTeamStatsPerGame() {
		try {
			String table = "team_stats_per_game ";

			// Get the TEAMS Data from API
			// uncomment method call to run....

			// We need just the last 5 seasons
			List<String> seasonList = new ArrayList<>();
			seasonList.add("2015"); // 1230
			seasonList.add("2016"); // 1230
			seasonList.add("2017"); // 1230
			seasonList.add("2018"); // 1271
			seasonList.add("2019"); // 1271

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager
					.getConnection(URL + "/" + DATABASE_NAME + "?" + "user=" + USERNAME + "&password=" + PASSWORD);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			for (String season : seasonList) {
				for (int i = 1; i < 1271; i++) {
					if ((season.equals("2015") || season.equals("2016") || season.equals("2017")) && i > 1230) {
						continue;
					}

					RootTeamStats teamStatsPerGame = ConsumeAPITest.getTeamStatsPerGame(season, leftPadding(i));
					if (teamStatsPerGame == null) {
						continue;
					}

					Teams team = teamStatsPerGame.getTeams(); // list of 1
					TeamSkaterStats away = team.getAway().getTeamStats().getTeamSkaterStats();
					TeamSkaterStats home = team.getHome().getTeamStats().getTeamSkaterStats();

					// AWAY /////////////////
					// PreparedStatements can use variables and are more efficient
					preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME + "." + table
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					// Parameters start with 1
					preparedStatement.setInt(1, i);
					preparedStatement.setString(2, season);
					preparedStatement.setString(3, "A");
					preparedStatement.setInt(4, away.getGoals());
					preparedStatement.setInt(5, away.getPim());
					preparedStatement.setFloat(6, away.getShots());
					preparedStatement.setFloat(7, fromStringToFloat(away.getPowerPlayPercentage()));
					preparedStatement.setFloat(8, away.getPowerPlayGoals());
					preparedStatement.setFloat(9, away.getPowerPlayOpportunities());
					preparedStatement.setFloat(10, fromStringToFloat(away.getFaceOffWinPercentage()));
					preparedStatement.setFloat(11, away.getBlocked());
					preparedStatement.setFloat(12, away.getTakeaways());
					preparedStatement.setFloat(13, away.getGiveaways());
					preparedStatement.setFloat(14, away.getHits());
					preparedStatement.executeUpdate();

					// HOME /////////////////
					// PreparedStatements can use variables and are more efficient
					preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME + "." + table
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					// Parameters start with 1
					preparedStatement.setInt(1, i);
					preparedStatement.setString(2, season);
					preparedStatement.setString(3, "H");
					preparedStatement.setInt(4, home.getGoals());
					preparedStatement.setInt(5, home.getPim());
					preparedStatement.setFloat(6, home.getShots());
					preparedStatement.setFloat(7, fromStringToFloat(home.getPowerPlayPercentage()));
					preparedStatement.setFloat(8, home.getPowerPlayGoals());
					preparedStatement.setFloat(9, home.getPowerPlayOpportunities());
					preparedStatement.setFloat(10, fromStringToFloat(home.getFaceOffWinPercentage()));
					preparedStatement.setFloat(11, home.getBlocked());
					preparedStatement.setFloat(12, home.getTakeaways());
					preparedStatement.setFloat(13, home.getGiveaways());
					preparedStatement.setFloat(14, home.getHits());
					preparedStatement.executeUpdate();
				}
			}

			System.out.println("=============== DONE INSERTING STATS PER TEAM PER SEASSON ===============");
			close();
		} catch (Exception e) {
			logger.error("ERROR ON INSERT METHOD");
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			close();
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////

// Insert team start per game Id for regular season
// https://statsapi.web.nhl.com/api/v1/game/2018020001/boxscore
	public void insertPlayerStatsPerGame() {
		try {
			String table = "player_stats_by_game";

			// Get the TEAMS Data from API
			// uncomment method call to run....

			// We need just the last 5 seasons
			List<String> seasonList = new ArrayList<>();
			seasonList.add("2015"); // 1230
			seasonList.add("2016"); // 1230
			seasonList.add("2017"); // 1230
			seasonList.add("2018"); // 1271
			seasonList.add("2019"); // 1271

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager
					.getConnection(URL + "/" + DATABASE_NAME + "?" + "user=" + USERNAME + "&password=" + PASSWORD);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			for (String season : seasonList) {
				for (int i = 1; i < 1271; i++) {
					if ((season.equals("2015") || season.equals("2016") || season.equals("2017")) && i > 1230) {
						continue;
					}

					// RootTeamStatsGeneric teamStatsPerGame =
					// ConsumeAPITest.getPlayerStatsPerGame(season, leftPadding(i));
					String jsonStr = ConsumeAPITest.getPlayerStatsPerGame(season, leftPadding(i));
					Gson g = new Gson();
					RootTeamStatsGeneric root = g.fromJson(jsonStr, RootTeamStatsGeneric.class);

					List<String> playerIdAwayList = new ArrayList<>();
					List<String> playerIdHomeList = new ArrayList<>();

					for (String playerIdAway : root.getTeams().getAway().getPlayers().keySet()) {
						playerIdAwayList.add(playerIdAway);
					}

					for (String playerIdHome : root.getTeams().getHome().getPlayers().keySet()) {
						playerIdHomeList.add(playerIdHome);
					}

					// String jsonStr2 = jsonStr.replace("\n", "").replace("\r", "");

					List<PlayerDynamic> playerDynamicAwayList = new ArrayList<>();
					for (String playerId : playerIdAwayList) {
						JSONObject jsonObject = new JSONObject(jsonStr);
						JSONObject playerStr = jsonObject.getJSONObject("teams").getJSONObject("away")
								.getJSONObject("players").getJSONObject(playerId);
						PlayerDynamic playerDynamicAway = g.fromJson(playerStr.toString(), PlayerDynamic.class);
						playerDynamicAway.setId(Integer.parseInt(playerId.replace("ID", "")));
						playerDynamicAwayList.add(playerDynamicAway);
					}

					List<PlayerDynamic> playerDynamicHomeList = new ArrayList<>();
					for (String playerId : playerIdHomeList) {
						JSONObject jsonObject = new JSONObject(jsonStr);
						JSONObject playerStr = jsonObject.getJSONObject("teams").getJSONObject("home")
								.getJSONObject("players").getJSONObject(playerId);
						PlayerDynamic playerDynamicHome = g.fromJson(playerStr.toString(), PlayerDynamic.class);
						playerDynamicHome.setId(Integer.parseInt(playerId.replace("ID", "")));
						playerDynamicHomeList.add(playerDynamicHome);
					}
					

					for (PlayerDynamic away : playerDynamicAwayList) {
						// AWAY /////////////////
						// PreparedStatements can use variables and are more efficient
						if(away.getStats().getSkaterStats() != null) {
							preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME + "." + table
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

							// Parameters start with 1
							preparedStatement.setInt(1, away.getId());
							preparedStatement.setInt(2, i);
							preparedStatement.setString(3, season);
							preparedStatement.setString(4, "A");
							preparedStatement.setString(5, away.getStats().getSkaterStats().getTimeOnIce());
							preparedStatement.setFloat(6, away.getStats().getSkaterStats().getAssists());
							preparedStatement.setFloat(7, away.getStats().getSkaterStats().getGoals());
							preparedStatement.setFloat(8, away.getStats().getSkaterStats().getShots());
							preparedStatement.setFloat(9, away.getStats().getSkaterStats().getHits());
							preparedStatement.setFloat(10, away.getStats().getSkaterStats().getPowerPlayGoals());
							preparedStatement.setFloat(11, away.getStats().getSkaterStats().getPowerPlayAssists());
							preparedStatement.setFloat(12, away.getStats().getSkaterStats().getPenaltyMinutes());
							preparedStatement.setFloat(13, away.getStats().getSkaterStats().getFaceOffWins());
							preparedStatement.setFloat(14, away.getStats().getSkaterStats().getFaceoffTaken());
							preparedStatement.setFloat(15, away.getStats().getSkaterStats().getTakeaways());
							preparedStatement.setFloat(16, away.getStats().getSkaterStats().getGiveaways());
							preparedStatement.setFloat(17, away.getStats().getSkaterStats().getShortHandedGoals());
							preparedStatement.setFloat(18, away.getStats().getSkaterStats().getShortHandedAssists());
							preparedStatement.setFloat(19, away.getStats().getSkaterStats().getBlocked());
							preparedStatement.setFloat(20, away.getStats().getSkaterStats().getPlusMinus());
							preparedStatement.setString(21, away.getStats().getSkaterStats().getEvenTimeOnIce());
							preparedStatement.setString(22, away.getStats().getSkaterStats().getPowerPlayTimeOnIce());
							preparedStatement.setString(23, away.getStats().getSkaterStats().getShortHandedTimeOnIce());
							preparedStatement.executeUpdate();
						}
						
					}

					for (PlayerDynamic home : playerDynamicHomeList) {
						// HOME /////////////////
						// PreparedStatements can use variables and are more efficient
						if(home.getStats().getSkaterStats() != null) {
							preparedStatement = connect.prepareStatement("insert into  " + DATABASE_NAME + "." + table
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

							// Parameters start with 1
							preparedStatement.setInt(1, home.getId());
							preparedStatement.setInt(2, i);
							preparedStatement.setString(3, season);
							preparedStatement.setString(4, "H");
							preparedStatement.setString(5, home.getStats().getSkaterStats().getTimeOnIce());
							preparedStatement.setFloat(6, home.getStats().getSkaterStats().getAssists());
							preparedStatement.setFloat(7, home.getStats().getSkaterStats().getGoals());
							preparedStatement.setFloat(8, home.getStats().getSkaterStats().getShots());
							preparedStatement.setFloat(9, home.getStats().getSkaterStats().getHits());
							preparedStatement.setFloat(10, home.getStats().getSkaterStats().getPowerPlayGoals());
							preparedStatement.setFloat(11, home.getStats().getSkaterStats().getPowerPlayAssists());
							preparedStatement.setFloat(12, home.getStats().getSkaterStats().getPenaltyMinutes());
							preparedStatement.setFloat(13, home.getStats().getSkaterStats().getFaceOffWins());
							preparedStatement.setFloat(14, home.getStats().getSkaterStats().getFaceoffTaken());
							preparedStatement.setFloat(15, home.getStats().getSkaterStats().getTakeaways());
							preparedStatement.setFloat(16, home.getStats().getSkaterStats().getGiveaways());
							preparedStatement.setFloat(17, home.getStats().getSkaterStats().getShortHandedGoals());
							preparedStatement.setFloat(18, home.getStats().getSkaterStats().getShortHandedAssists());
							preparedStatement.setFloat(19, home.getStats().getSkaterStats().getBlocked());
							preparedStatement.setFloat(20, home.getStats().getSkaterStats().getPlusMinus());
							preparedStatement.setString(21, home.getStats().getSkaterStats().getEvenTimeOnIce());
							preparedStatement.setString(22, home.getStats().getSkaterStats().getPowerPlayTimeOnIce());
							preparedStatement.setString(23, home.getStats().getSkaterStats().getShortHandedTimeOnIce());
							preparedStatement.executeUpdate();
						}
						
					}

				}
			}

			System.out.println("=============== DONE INSERTING STATS PER TEAM PER SEASSON ===============");
			close();
		} catch (Exception e) {
			logger.error("ERROR ON INSERT METHOD");
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			close();
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////

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

	// convert String to int
	private int fromStringToInt(String numStr) {
		return Integer.parseInt(numStr);
	}

	// convert String to int
	private float fromStringToFloat(String numStr) {
		return Float.parseFloat(numStr);
	}

	private String leftPadding(int num) {
		return StringUtils.leftPad(String.valueOf(num), 4, "0");
	}

	public String generateGameId(int i) {
		return leftPadding(i);
	}
}
