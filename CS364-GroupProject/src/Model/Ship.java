package Model;

public class Ship {
	
    private int sid;
    private int cid;
    private String name;
    private String type;

    public Ship() {}

    public Ship(int sid, int cid, String name, String type) {
        this.sid = sid;
        this.cid = cid;
        this.name = name;
        this.type = type;
    }

    public int getSid() {
    	return sid;
    }
    
    public int getCid() {
    	return cid;
    }

    public String getName() { 
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public String getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
