package Model;

public class Participate {
    private int sid;
    private int bid;
    private Boolean wasSunk;

    public Participate() {}

    public Participate(int sid, int bid, Boolean wasSunk) {
        this.sid = sid;
        this.bid = bid;
        this.wasSunk = wasSunk;
    }

    public int getSid() {
    	return sid;
    }
    
    public void setSid(int sid) { this.sid = sid; }

    public int getBid() { 
    	return bid;
    }
    
    public void setBid(int bid) {
    	this.bid = bid;
    }

    public Boolean getWasSunk() {
    	return wasSunk;
    }
    
    public void setWasSunk(Boolean wasSunk) {
    	this.wasSunk = wasSunk;
    }

    @Override
    public String toString() {
        return "Participate{" +
                "sid=" + sid +
                ", bid=" + bid +
                ", wasSunk=" + wasSunk +
                '}';
    }
}

