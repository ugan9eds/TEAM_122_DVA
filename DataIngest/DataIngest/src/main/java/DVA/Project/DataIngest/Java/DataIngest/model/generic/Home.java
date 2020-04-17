package DVA.Project.DataIngest.Java.DataIngest.model.generic;

import java.util.List;
import java.util.Map;

import DVA.Project.DataIngest.Java.DataIngest.model.Person;
import DVA.Project.DataIngest.Java.DataIngest.model.Team;
import DVA.Project.DataIngest.Java.DataIngest.model.TeamStats;

public class Home {
	private Team team;

    private TeamStats teamStats;

    private Map<String, Person> players;
//    private List<Integer> goalies;
//
//    private List<Integer> skaters;
//
//    private List<String> onIce;
//
//    private List<String> onIcePlus;
//
//    private List<Integer> scratches;
//
//    private List<String> penaltyBox;
//
//    private List<Coaches> coaches;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public TeamStats getTeamStats() {
		return teamStats;
	}

	public void setTeamStats(TeamStats teamStats) {
		this.teamStats = teamStats;
	}

	public Map<String, Person> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, Person> players) {
		this.players = players;
	}

	
    
    
}

