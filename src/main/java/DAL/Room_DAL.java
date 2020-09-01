package DAL;

import Persistance.Room;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Room_DAL {
    public List<Room> getAllRoom() throws SQLException {
        List<Room> roomList=new ArrayList<>();
        CallableStatement cst=DbUtil.getConnection().prepareCall("{call getAllRoom()}");
        ResultSet resultSet=cst.executeQuery();
        while(resultSet.next()){
            roomList.add(getRoom(resultSet));
        }
        return roomList;
    }
    public List<Room> getRoom() throws SQLException {
        List<Room> roomList=new ArrayList<>();
        CallableStatement cst=DbUtil.getConnection().prepareCall("{call view_room()}");
        ResultSet resultSet=cst.executeQuery();
        while(resultSet.next()){
            roomList.add(getRoom(resultSet));
        }
        return roomList;
    }
    public Room getRoom_by_id(String a) throws SQLException {
        Room room=new Room();
        PreparedStatement preparedStatement= DbUtil.getConnection().prepareStatement("select*from hotel_real.room where room.ID=?");
        preparedStatement .setString(1,a);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            room.setRoom_id(resultSet.getString(1));
            room.setRoom_type(resultSet.getString(2));
            room.setFloor(resultSet.getInt(3));
            room.setPrice(resultSet.getInt(4));
            room.setAvailability(resultSet.getString(5));
            room.setStatus(resultSet.getString(6));
        }
        return room;
    }
    public Room getRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setRoom_id(resultSet.getString(1));
        room.setRoom_type(resultSet.getString(2));
        room.setFloor(resultSet.getInt(3));
        room.setPrice(resultSet.getInt(4));
        room.setAvailability(resultSet.getString(5));
        room.setStatus(resultSet.getString(6));
        return room;
    }
    public void update_availability(String a) throws SQLException {
        PreparedStatement preparedStatement= DbUtil.getConnection().prepareStatement("update hotel_real.room set room.Availability='1' where room.ID=?");
        preparedStatement .setString(1,a);
        preparedStatement.executeUpdate();
    }
    public void update_availability1(String a) throws SQLException {
        PreparedStatement preparedStatement= DbUtil.getConnection().prepareStatement("update hotel_real.room set room.Availability='0' where room.ID=?");
        preparedStatement .setString(1,a);
        preparedStatement.executeUpdate();
    }
    public void update_status(String a,String b) throws SQLException {
        PreparedStatement preparedStatement=DbUtil.getConnection().prepareStatement("update hotel_real.room set room.Room_Status=? where room.ID=?");
        preparedStatement.setString(1,a);
        preparedStatement.setString(2,b);
        preparedStatement.execute();
    }
    public boolean check_id(String id) throws SQLException {
        CallableStatement callableStatement = DbUtil.getConnection().prepareCall("{call check_id(?)}");
        callableStatement.setString(1,id);
        ResultSet resultSet = callableStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        return false;
    }
}
