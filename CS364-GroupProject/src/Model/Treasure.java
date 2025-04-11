package Model;

public class Treasure {
    private int tid;
    private String value;
    private String location;

    private int iid;
    private int pid;

    public Treasure() {}

    public Treasure(int tid, String value, String location, int iid, int pid) {
        this.tid = tid;
        this.value = value;
        this.location = location;
        this.iid = iid;
        this.pid = pid;
    }

    public int getTid() {
    	return tid;
    }
    
    public void setTid(int tid) {
    	this.tid = tid;
    }

    public String getValue() {
    	return value;
    }
    
    public void setValue(String value) {
    	this.value = value;
    }

    public String getLocation() {
    	return location;
    }
    
    public void setLocation(String location) {
    	this.location = location;
    }

    public int getIid() {
    	return iid;
    }
    
    public void setIid(int iid) {
    	this.iid = iid;
    }

    public int getPid() {
    	return pid;
    }
    
    public void setPid(int pid) {
    	this.pid = pid;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "tid=" + tid +
                ", value='" + value + '\'' +
                ", location='" + location + '\'' +
                ", iid=" + iid +
                ", pid=" + pid +
                '}';
    }
}
