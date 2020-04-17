package DVA.Project.DataIngest.Java.DataIngest.model;

import java.util.List;

public class Roster {
	private List<Rosterunit> roster;

	private String link;

	public void setRoster(List<Rosterunit> roster) {
		this.roster = roster;
	}

	public List<Rosterunit> getRoster() {
		return this.roster;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}
}