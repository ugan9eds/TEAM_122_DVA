package DVA.Project.DataIngest.Java.DataIngest.model.generic;

import java.util.List;

import DVA.Project.DataIngest.Java.DataIngest.model.Conference;
import DVA.Project.DataIngest.Java.DataIngest.model.Division;
import DVA.Project.DataIngest.Java.DataIngest.model.Franchise;
import DVA.Project.DataIngest.Java.DataIngest.model.Roster;
import DVA.Project.DataIngest.Java.DataIngest.model.TeamStats;
import DVA.Project.DataIngest.Java.DataIngest.model.Venue;

public class Teams {
	private int id;

	private String name;

	private String link;

	private Away away;

	private Home home;

	private Venue venue;

	private String abbreviation;

	private String teamName;

	private String locationName;

	private String firstYearOfPlay;

	private Division division;

	private Conference conference;

	private Franchise franchise;

	private Roster roster;

	private List<TeamStats> teamStats;

	private String shortName;

	private String officialSiteUrl;

	private int franchiseId;

	private String active;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public Away getAway() {
		return away;
	}

	public void setAway(Away away) {
		this.away = away;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setFirstYearOfPlay(String firstYearOfPlay) {
		this.firstYearOfPlay = firstYearOfPlay;
	}

	public String getFirstYearOfPlay() {
		return this.firstYearOfPlay;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Division getDivision() {
		return this.division;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public Conference getConference() {
		return this.conference;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

	public Franchise getFranchise() {
		return this.franchise;
	}

	public void setTeamStats(List<TeamStats> teamStats) {
		this.teamStats = teamStats;
	}

	public List<TeamStats> getTeamStats() {
		return this.teamStats;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setOfficialSiteUrl(String officialSiteUrl) {
		this.officialSiteUrl = officialSiteUrl;
	}

	public String getOfficialSiteUrl() {
		return this.officialSiteUrl;
	}

	public void setFranchiseId(int franchiseId) {
		this.franchiseId = franchiseId;
	}

	public int getFranchiseId() {
		return this.franchiseId;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getActive() {
		return this.active;
	}
}
