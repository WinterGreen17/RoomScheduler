
import java.sql.Date;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmy5157
 */
public class WaitlistEntry {
    private String faculty;
    private int seats;
    private Date date;
    private Timestamp timestamp;

    public WaitlistEntry(String faculty, Date date, int seats , Timestamp timestamp) {
        this.faculty = faculty;
        this.seats = seats;
        this.date = date;
        this.timestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }


    public int getSeats() {
        return seats;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
}
