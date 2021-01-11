
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class WaitlistQueries {
    private static Connection connection;
    private static ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement cancelWaitlistEntry;
    private static PreparedStatement getWaitlistByFaculty;
    public static PreparedStatement getWaitlistedSeats;
    private static ResultSet resultSet;
    
    public static void addWaitlistEntry(WaitlistEntry waitlist)
    {
        connection = DBConnection.getConnection();
        try
        {
            addWaitlistEntry = connection.prepareStatement("insert into waitlist (FACULTY,DATE,SEATS,TIMESTAMP) values (?,?,?,?)");
            addWaitlistEntry.setString(1,waitlist.getFaculty());
            addWaitlistEntry.setDate(2, waitlist.getDate());
            addWaitlistEntry.setInt(3, waitlist.getSeats());
            addWaitlistEntry.setTimestamp(4, waitlist.getTimestamp());
            addWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getWaitlistByDate()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> waitlist = new ArrayList<String>();
        try
        {
            getWaitlistByDate = connection.prepareStatement("SELECT date FROM waitlist");
            resultSet = getWaitlistByDate.executeQuery();
            
            while(resultSet.next())
            {
                waitlist.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
        
    }
    
    public static ArrayList<WaitlistEntry> getWaitlist()
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlist = connection.prepareStatement("SELECT * FROM waitlist ORDER BY date, timestamp");
            resultSet = getWaitlist.executeQuery();
            
            while(resultSet.next())
            {
                waitlist.add(new WaitlistEntry(resultSet.getString(1),resultSet.getDate(2),resultSet.getInt(3),resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
        
    }
    
    public static ArrayList<Integer> getWaitlistedSeats()
    {
        connection = DBConnection.getConnection();
        ArrayList<Integer> waitlistSeats = new ArrayList<Integer>();
        try
        {
            getWaitlistedSeats = connection.prepareStatement("SELECT seats FROM waitlist ORDER BY date, timestamp");
            resultSet = getWaitlistedSeats.executeQuery();
            
            while(resultSet.next())
            {
                waitlistSeats.add(resultSet.getInt(3));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistSeats;
        
    }
    
    public static void cancelWaitlistEntry(WaitlistEntry waitlist)
    {
        connection = DBConnection.getConnection();
        try
        {
            cancelWaitlistEntry = connection.prepareStatement("DELETE FROM WAITLIST WHERE FACULTY = ? AND DATE = ? AND SEATS = ? AND TIMESTAMP = ?");
            cancelWaitlistEntry.setString(1,waitlist.getFaculty());
            cancelWaitlistEntry.setDate(2, waitlist.getDate());
            cancelWaitlistEntry.setInt(3, waitlist.getSeats());
            cancelWaitlistEntry.setTimestamp(4, waitlist.getTimestamp());
            cancelWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty)
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistF = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("SELECT * FROM waitlist where faculty = ?");
            getWaitlistByFaculty.setString(1, faculty);
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                waitlistF.add(new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2),resultSet.getInt(3),resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistF;
    }
}
