package DVA.Project.DataIngest.Java.DataIngest.model;

import java.util.List;


public class RootTeamStats {
	private String copyright;

	private Teams teams;

	private List<Officials> officials;

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCopyright() {
		return this.copyright;
	}

	public void setTeams(Teams teams) {
		this.teams = teams;
	}

	public Teams getTeams() {
		return this.teams;
	}

	public void setOfficials(List<Officials> officials) {
		this.officials = officials;
	}

	public List<Officials> getOfficials() {
		return this.officials;
	}
}
