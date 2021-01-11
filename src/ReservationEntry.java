
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
public class ReservationEntry {
    private String faculty;
    private String room;
    private int seats;
    private Date date;
    private Timestamp timestamp;

    public ReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp) {
        this.faculty = faculty;
        this.room = room;
        this.seats = seats;
        this.date = date;
        this.timestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getRoom() {
        return room;
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
