package DVA.Project.DataIngest.Java.DataIngest.model;

import java.util.ArrayList;
import java.util.List;

public class Root
{
    private String copyright;

    private List<Teams> teams;
    
    private List<Officials> officials;

    public void setCopyright(String copyright){
        this.copyright = copyright;
    }
    public String getCopyright(){
        return this.copyright;
    }
    public void setTeams(List<Teams> teams){
        this.teams = teams;
    }
    public List<Teams> getTeams(){
        return this.teams;
    }
	public List<Officials> getOfficials() {
		return officials;
	}
	public void setOfficials(List<Officials> officials) {
		this.officials = officials;
	}
	
}
