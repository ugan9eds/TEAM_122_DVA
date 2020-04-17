package DVA.Project.DataIngest.Java.DataIngest.model;

public class Franchise
{
    private int franchiseId;

    private String teamName;

    private String link;

    public void setFranchiseId(int franchiseId){
        this.franchiseId = franchiseId;
    }
    public int getFranchiseId(){
        return this.franchiseId;
    }
    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
    public String getTeamName(){
        return this.teamName;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }
}
