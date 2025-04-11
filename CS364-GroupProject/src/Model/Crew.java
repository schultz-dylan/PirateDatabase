package Model;


public class Crew {

    private short cid;

    private String name;

    private String location;

    public Crew() {}

    public Crew(short cid, String name, String location) {
    	this.cid = cid;
        this.name = name;
        this.location = location;
    }
    
    public Crew(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Short getCid() {
        return cid;
    }

    public void setCid(Short cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}