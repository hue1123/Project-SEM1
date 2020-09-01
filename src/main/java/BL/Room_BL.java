package BL;

import DAL.Room_DAL;
import Persistance.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Room_BL {
    static Room_DAL room_dal = new Room_DAL();

    public List<Room> getAllRoom() throws SQLException {
        return room_dal.getAllRoom();
    }

    public static boolean check_id(String a) throws SQLException {
        return room_dal.check_id(a);
    }

    public void update_Availability(String a) throws SQLException {
        room_dal.update_availability(a);
    }

    public List<Room> view_room() throws SQLException {
        return room_dal.getRoom();
    }

    public void update_availability1(String a) throws SQLException {
        room_dal.update_availability1(a);
    }

    public void update_status(String a, String b) throws SQLException {
        room_dal.update_status(a, b);
    }

    public Room getRoom_by_id(String a) throws SQLException {
        return room_dal.getRoom_by_id(a);
    }

}