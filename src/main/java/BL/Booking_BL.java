 package BL;

import DAL.Booking_DAL;
import DAL.DbUtil;
import Persistance.Booking;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Booking_BL {
    Booking_DAL booking_dal = new Booking_DAL();

    public Booking getBooking(String cmnd) throws SQLException {
        return booking_dal.getBooking(cmnd);
    }
    public void add(String name_user, String cmnd, Date check_in, Date check_out, Date booking, String booking_status) throws SQLException {
        booking_dal.add(name_user, cmnd, check_in, check_out, booking, booking_status);
    }

    public void addMoreRoom(int a, String b) throws SQLException {
        booking_dal.addMoreRoom(a, b);
    }

    public void insert_belong(int a, String b) throws SQLException {
        booking_dal.insert_belong(a, b);
    }

    public ResultSet view_booking(int a) throws SQLException {
        return booking_dal.view_booking(a);
    }

    public List<Booking> view_booking_list() throws SQLException {
        return booking_dal.view_booking_list();
    }

    public void update_status(int id) throws SQLException {
        booking_dal.upadte_status(id);
    }

    public void delete_belong(int id) throws SQLException {
        booking_dal.delete_belong(id);
    }
    public  ResultSet getBelong(int id) throws SQLException {
       return booking_dal.getBelong(id);
    }
}