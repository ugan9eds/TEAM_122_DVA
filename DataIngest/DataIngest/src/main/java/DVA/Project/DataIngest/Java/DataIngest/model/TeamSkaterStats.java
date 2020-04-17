package DVA.Project.DataIngest.Java.DataIngest.model;

public class TeamSkaterStats {
	private int goals;

	private int pim;

	private int shots;

	private String powerPlayPercentage;

	private int powerPlayGoals;

	private int powerPlayOpportunities;

	private String faceOffWinPercentage;

	private int blocked;

	private int takeaways;

	private int giveaways;

	private int hits;

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getGoals() {
		return this.goals;
	}

	public void setPim(int pim) {
		this.pim = pim;
	}

	public int getPim() {
		return this.pim;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public int getShots() {
		return this.shots;
	}

	public void setPowerPlayPercentage(String powerPlayPercentage) {
		this.powerPlayPercentage = powerPlayPercentage;
	}

	public String getPowerPlayPercentage() {
		return this.powerPlayPercentage;
	}

	public void setPowerPlayGoals(int powerPlayGoals) {
		this.powerPlayGoals = powerPlayGoals;
	}

	public int getPowerPlayGoals() {
		return this.powerPlayGoals;
	}

	public void setPowerPlayOpportunities(int powerPlayOpportunities) {
		this.powerPlayOpportunities = powerPlayOpportunities;
	}

	public int getPowerPlayOpportunities() {
		return this.powerPlayOpportunities;
	}

	public void setFaceOffWinPercentage(String faceOffWinPercentage) {
		this.faceOffWinPercentage = faceOffWinPercentage;
	}

	public String getFaceOffWinPercentage() {
		return this.faceOffWinPercentage;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public int getBlocked() {
		return this.blocked;
	}

	public void setTakeaways(int takeaways) {
		this.takeaways = takeaways;
	}

	public int getTakeaways() {
		return this.takeaways;
	}

	public void setGiveaways(int giveaways) {
		this.giveaways = giveaways;
	}

	public int getGiveaways() {
		return this.giveaways;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getHits() {
		return this.hits;
	}
}
