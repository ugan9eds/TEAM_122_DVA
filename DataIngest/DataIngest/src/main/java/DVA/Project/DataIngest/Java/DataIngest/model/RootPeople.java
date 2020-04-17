package DVA.Project.DataIngest.Java.DataIngest.model;

import java.util.List;

public class RootPeople {
	private String copyright;

	private List<People> people;

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCopyright() {
		return this.copyright;
	}

	public void setPeople(List<People> people) {
		this.people = people;
	}

	public List<People> getPeople() {
		return this.people;
	}
}
