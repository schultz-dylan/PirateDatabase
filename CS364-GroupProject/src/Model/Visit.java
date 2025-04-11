package Model;

import java.util.Date;

public class Visit {
    private int iid;        // Foreign Key to Island
    private int sid;        // Foreign Key to Ship
    private Date date;      // Part of composite primary key

    public Visit() {}

    public Visit(int iid, int sid, Date date) {
        this.iid = iid;
        this.sid = sid;
        this.date = date;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "iid=" + iid +
                ", sid=" + sid +
                ", date=" + date +
                '}';
    }
}
