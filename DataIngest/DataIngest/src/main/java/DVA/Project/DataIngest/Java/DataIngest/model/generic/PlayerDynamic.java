package DVA.Project.DataIngest.Java.DataIngest.model.generic;

import DVA.Project.DataIngest.Java.DataIngest.model.Person;
import DVA.Project.DataIngest.Java.DataIngest.model.SkaterStats;
import DVA.Project.DataIngest.Java.DataIngest.model.Stats;

public class PlayerDynamic {
	private int id;
	private Stats stats;

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
