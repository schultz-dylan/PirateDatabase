package Model;

public class Island {
    private int iid;
    private String name;
    private String location;

    public Island() {}

    public Island(int iid, String name, String location) {
        this.iid = iid;
        this.name = name;
        this.location = location;
    }

    public int getIid() {
    	return iid;
    }
    
    public void setIid(int iid) {
    	this.iid = iid;
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
        return "Island{" +
                "iid=" + iid +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
