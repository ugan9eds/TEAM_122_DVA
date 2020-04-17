package DVA.Project.DataIngest.Java.DataIngest.model;

import java.util.List;

public class RootDivisions {
	private String copyright;

	private List<Divisions> divisions;

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCopyright() {
		return this.copyright;
	}

	public void setDivisions(List<Divisions> divisions) {
		this.divisions = divisions;
	}

	public List<Divisions> getDivisions() {
		return this.divisions;
	}
}
