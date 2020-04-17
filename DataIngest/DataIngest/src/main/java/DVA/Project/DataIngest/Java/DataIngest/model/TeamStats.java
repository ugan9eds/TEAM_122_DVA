package DVA.Project.DataIngest.Java.DataIngest.model;

import java.util.List;

public class TeamStats {
	private Type type;

	private List<Splits> splits;

	private TeamSkaterStats teamSkaterStats;

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return this.type;
	}

	public void setSplits(List<Splits> splits) {
		this.splits = splits;
	}

	public List<Splits> getSplits() {
		return this.splits;
	}

	public TeamSkaterStats getTeamSkaterStats() {
		return teamSkaterStats;
	}

	public void setTeamSkaterStats(TeamSkaterStats teamSkaterStats) {
		this.teamSkaterStats = teamSkaterStats;
	}

}
