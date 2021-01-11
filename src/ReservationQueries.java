
// import static Faculty.getFacultyList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmy5157
 */
public class ReservationQueries {
    private static Connection connection;
    private static ArrayList<ReservationEntry> reservation = new ArrayList<ReservationEntry>();
    private static PreparedStatement getReservationByDate;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement getReservation;
    private static PreparedStatement cancelReservation;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getReservationsByRoom;
    // private static PreparedStatement deleteReservation;
    private static ResultSet resultSet;
    
    public static void addReservationEntry(ReservationEntry reservation)
    {
        connection = DBConnection.getConnection();
        try
        {
            addReservationEntry = connection.prepareStatement("insert into reservation (FACULTY, ROOM, SEATS, DATE, TIMESTAMP) VALUES(?,?,?,?,?)");
            addReservationEntry.setString(1, reservation.getFaculty());
            addReservationEntry.setString(2, reservation.getRoom());
            addReservationEntry.setInt(3, reservation.getSeats());
            addReservationEntry.setDate(4, reservation.getDate());
            addReservationEntry.setTimestamp(5, reservation.getTimestamp());
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ReservationEntry> getReservationByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservation = new ArrayList<ReservationEntry>();
        try
        {
            getReservationByDate = connection.prepareStatement("SELECT FACULTY,ROOM,DATE,SEATS,TIMESTAMP FROM reservation where DATE=?");
            getReservationByDate.setDate(1, date);
            resultSet = getReservationByDate.executeQuery();
            
            while(resultSet.next())
            {
                reservation.add(new ReservationEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation;
        
    }
    
    public static ArrayList<String> getRoomsReservedByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> reservation = new ArrayList<String>();
        try
        {
            getRoomsReservedByDate = connection.prepareStatement("SELECT room, date FROM reservation WHERE date = ?");
            getRoomsReservedByDate.setDate(1, date);
            resultSet = getRoomsReservedByDate.executeQuery();
            
            while(resultSet.next())
            {
                reservation.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation;
        
    }
      
    
    public static ArrayList<ReservationEntry> getReservations()
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservation = new ArrayList<ReservationEntry>();
        try
        {
            getReservation = connection.prepareStatement("SELECT * FROM reservation");
            resultSet = getReservation.executeQuery();
            
            while(resultSet.next())
            {
                reservation.add(new ReservationEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation;
    }
    
    
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservation = new ArrayList<ReservationEntry>();
        try
        {
            getReservationsByFaculty = connection.prepareStatement("SELECT FACULTY,ROOM,DATE,SEATS,TIMESTAMP FROM reservation where faculty = ?");
            getReservationsByFaculty.setString(1, faculty);
            resultSet = getReservationsByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                reservation.add(new ReservationEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByRoom(String room)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservation = new ArrayList<ReservationEntry>();
        try
        {
            getReservationsByRoom = connection.prepareStatement("SELECT FACULTY,ROOM,DATE,SEATS,TIMESTAMP FROM reservation where room = ?");
            getReservationsByRoom.setString(1, room);
            resultSet = getReservationsByRoom.executeQuery();
            
            while(resultSet.next())
            {
                reservation.add(new ReservationEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation;
    }
    
        public static void cancelReservation(ReservationEntry reservation)
    {
        connection = DBConnection.getConnection();
        try
        {
            cancelReservation = connection.prepareStatement("DELETE FROM reservation WHERE FACULTY = ? AND ROOM = ? AND SEATS = ? AND DATE = ? AND TIMESTAMP = ?");
            cancelReservation.setString(1, reservation.getFaculty());
            cancelReservation.setString(2, reservation.getRoom());
            cancelReservation.setInt(3, reservation.getSeats());
            cancelReservation.setDate(4, reservation.getDate());
            cancelReservation.setTimestamp(5, reservation.getTimestamp());
            cancelReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
        
    }

