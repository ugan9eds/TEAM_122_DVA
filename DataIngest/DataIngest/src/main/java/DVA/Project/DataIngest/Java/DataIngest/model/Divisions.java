package DVA.Project.DataIngest.Java.DataIngest.model;

public class Divisions {
	private int id;

	private String name;

	private String nameShort;

	private String link;

	private String abbreviation;

	private Conference conference;

	private boolean active;

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

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public String getNameShort() {
		return this.nameShort;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public Conference getConference() {
		return this.conference;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActive() {
		return this.active;
	}
}
