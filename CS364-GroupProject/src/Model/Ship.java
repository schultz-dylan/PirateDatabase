package Model;

public class Ship {
	
    private int sid;
    private String name;
    private String type;

    public Ship() {}

    public Ship(int sid, String name, String type) {
        this.sid = sid;
        this.name = name;
        this.type = type;
    }

    public int getSid() {
    	return sid;
    }
    
    public void setSid(int sid) { this.sid = sid; }

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
