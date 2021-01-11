
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
public class RoomQueries { // this class needs a THICC revise
    private static Connection connection;
    // private static ArrayList<String> room = new ArrayList<String>();
    private static PreparedStatement getAllPossibleRooms; // need help w this...

    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom; 
    private static PreparedStatement getAllRooms;
    private static ResultSet resultSet;
    
    
    public static void addRoom(RoomEntry roomE)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (NAME,SEATS) VALUES(?,?)");
            addRoom.setString(1, roomE.getName());
            addRoom.setInt(2, roomE.getSeats());
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllPossibleRooms(int seat) // get room names
    {
        connection = DBConnection.getConnection();
        ArrayList<String> room = new ArrayList<String>();
        
        try
        {            
            getAllPossibleRooms = connection.prepareStatement("SELECT name FROM JAVA.ROOMS WHERE seats >= ? order by seats asc");
            getAllPossibleRooms.setInt(1, seat);            
            resultSet = getAllPossibleRooms.executeQuery();
            
            while(resultSet.next())
            {
                room.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return room;
        
    }
    
    public static ArrayList<String> getAllRooms() // get room names
    {
        connection = DBConnection.getConnection();
        ArrayList<String> room = new ArrayList<String>();
        
        try
        {            
            getAllRooms = connection.prepareStatement("SELECT name FROM JAVA.ROOMS");     
            resultSet = getAllRooms.executeQuery();
            
            while(resultSet.next())
            {
                room.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return room;
        
    }
    
     public static void dropRoom(String room)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("DELETE FROM rooms WHERE NAME = ?");
            dropRoom.setString(1, room);
            dropRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
}
