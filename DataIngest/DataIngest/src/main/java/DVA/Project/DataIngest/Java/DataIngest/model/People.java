package DVA.Project.DataIngest.Java.DataIngest.model;

import java.sql.Date;

public class People {
	private int id;

	private String fullName;

	private String link;

	private String firstName;

	private String lastName;

	private String primaryNumber;

	private String birthDate;

	private int currentAge;

	private String birthCity;

	private String birthStateProvince;

	private String birthCountry;

	private String nationality;

	private String height;

	private int weight;

	private String active;

	private String alternateCaptain;

	private String captain;

	private String rookie;

	private String shootsCatches;

	private String rosterStatus;

	private CurrentTeam currentTeam;

	private PrimaryPosition primaryPosition;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}

	public String getPrimaryNumber() {
		return this.primaryNumber;
	}

//	public void setBirthDate(DateTime birthDate) {
//		this.birthDate = birthDate;
//	}
//
//	public DateTime getBirthDate() {
//		return this.birthDate;
//	}

	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}

	public int getCurrentAge() {
		return this.currentAge;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthCity() {
		return this.birthCity;
	}

	public void setBirthStateProvince(String birthStateProvince) {
		this.birthStateProvince = birthStateProvince;
	}

	public String getBirthStateProvince() {
		return this.birthStateProvince;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getBirthCountry() {
		return this.birthCountry;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeight() {
		return this.height;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getActive() {
		return this.active;
	}

	public void setAlternateCaptain(String alternateCaptain) {
		this.alternateCaptain = alternateCaptain;
	}

	public String getAlternateCaptain() {
		return this.alternateCaptain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	public String getCaptain() {
		return this.captain;
	}

	public void setRookie(String rookie) {
		this.rookie = rookie;
	}

	public String getRookie() {
		return this.rookie;
	}

	public void setShootsCatches(String shootsCatches) {
		this.shootsCatches = shootsCatches;
	}

	public String getShootsCatches() {
		return this.shootsCatches;
	}

	public void setRosterStatus(String rosterStatus) {
		this.rosterStatus = rosterStatus;
	}

	public String getRosterStatus() {
		return this.rosterStatus;
	}

	public void setCurrentTeam(CurrentTeam currentTeam) {
		this.currentTeam = currentTeam;
	}

	public CurrentTeam getCurrentTeam() {
		return this.currentTeam;
	}

	public void setPrimaryPosition(PrimaryPosition primaryPosition) {
		this.primaryPosition = primaryPosition;
	}

	public PrimaryPosition getPrimaryPosition() {
		return this.primaryPosition;
	}
}
