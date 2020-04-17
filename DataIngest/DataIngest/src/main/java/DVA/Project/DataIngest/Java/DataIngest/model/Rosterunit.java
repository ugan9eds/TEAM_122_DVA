package DVA.Project.DataIngest.Java.DataIngest.model;

public class Rosterunit {
	 private Person person;

	    private String jerseyNumber;

	    private Position position;

	    public void setPerson(Person person){
	        this.person = person;
	    }
	    public Person getPerson(){
	        return this.person;
	    }
	    public void setJerseyNumber(String jerseyNumber){
	        this.jerseyNumber = jerseyNumber;
	    }
	    public String getJerseyNumber(){
	        return this.jerseyNumber;
	    }
	    public void setPosition(Position position){
	        this.position = position;
	    }
	    public Position getPosition(){
	        return this.position;
	    }
}
