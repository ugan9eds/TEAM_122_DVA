package DVA.Project.DataIngest.Java.DataIngest.model;

public class Officials {
	private Official official;

	private String officialType;

	public void setOfficial(Official official) {
		this.official = official;
	}

	public Official getOfficial() {
		return this.official;
	}

	public void setOfficialType(String officialType) {
		this.officialType = officialType;
	}

	public String getOfficialType() {
		return this.officialType;
	}
}
