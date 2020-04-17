package DVA.Project.DataIngest.Java.DataIngest.model;

public class Splits {
	private Stat stat;

    private Team team;

    public void setStat(Stat stat){
        this.stat = stat;
    }
    public Stat getStat(){
        return this.stat;
    }
    public void setTeam(Team team){
        this.team = team;
    }
    public Team getTeam(){
        return this.team;
    }
}
