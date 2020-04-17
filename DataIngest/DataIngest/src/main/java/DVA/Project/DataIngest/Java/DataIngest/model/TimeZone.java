package DVA.Project.DataIngest.Java.DataIngest.model;

public class TimeZone
{
    private String id;

    private int offset;

    private String tz;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setOffset(int offset){
        this.offset = offset;
    }
    public int getOffset(){
        return this.offset;
    }
    public void setTz(String tz){
        this.tz = tz;
    }
    public String getTz(){
        return this.tz;
    }
}