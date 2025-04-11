package Model;

import java.util.Date;

public class Battle {
    private int bid;
    private short casualties;
    private String winner;
    private String name;
    private int lootAmount;
    private String location;
    private Date date;

    public Battle() {}

    public Battle(int bid, short casualties, String winner, String name, int lootAmount, String location, Date date) {
        this.bid = bid;
        this.casualties = casualties;
        this.winner = winner;
        this.name = name;
        this.lootAmount = lootAmount;
        this.location = location;
        this.date = date;
    }

    public int getBid() {
    	return bid;
    }
    
    public void setBid(int bid) {
    	this.bid = bid;
    }

    public short getCasualties() {
    	return casualties;
    }
    
    public void setCasualties(short casualties) {
    	this.casualties = casualties;
    }

    public String getWinner() {
    	return winner;
    }
    
    public void setWinner(String winner) {
    	this.winner = winner;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public int getLootAmount() {
    	return lootAmount;
    }
    
    public void setLootAmount(int lootAmount) {
    	this.lootAmount = lootAmount;
    }

    public String getLocation() {
    	return location;
    }
    
    public void setLocation(String location) {
    	this.location = location;
    }

    public Date getDate() {
    	return date;
    }
    
    public void setDate(Date date) {
    	this.date = date;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "bid=" + bid +
                ", casualties=" + casualties +
                ", winner='" + winner + '\'' +
                ", name='" + name + '\'' +
                ", lootAmount=" + lootAmount +
                ", location='" + location + '\'' +
                ", date=" + date +
                '}';
    }
}
