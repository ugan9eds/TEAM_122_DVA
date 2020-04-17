package DVA.Project.DataIngest.Java.DataIngest.model;

public class Venue
{
    private String name;

    private String link;

    private String city;

    private TimeZone timeZone;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setTimeZone(TimeZone timeZone){
        this.timeZone = timeZone;
    }
    public TimeZone getTimeZone(){
        return this.timeZone;
    }
}
