package DAL;

import Persistance.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Booking_DAL {
    public Booking getBooking(String cmnd) throws SQLException {
        Booking booking1 = new Booking();
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("select * from hotel_real.booking where booking.CMND=?");
        preparedStatement.setString(1, cmnd);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            booking1.setID(resultSet.getInt(1));
            booking1.setName_User(resultSet.getString(2));
            booking1.setCMND(resultSet.getString(3));
            booking1.setDate_In(resultSet.getDate(4));
            booking1.setDate_Out(resultSet.getDate(5));
            booking1.setBooking_Date(resultSet.getDate(6));
            booking1.setBooking_Status(resultSet.getString(7));
        }
        return booking1;
    }

    public void add(String name_user, String cmnd, Date check_in, Date check_out, Date booking, String booking_status) throws SQLException {
        booking_status = "0";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("insert into hotel_real.booking(Name_User,CMND,Check_In_Date,Check_Out_Date,Booking_Date,Booking_Status) values (?,?,?,?,?,?)");
        preparedStatement.setString(1, name_user);
        preparedStatement.setString(2, cmnd);
        preparedStatement.setDate(3, check_in);
        preparedStatement.setDate(4, check_out);
        preparedStatement.setDate(5, booking);
        preparedStatement.setString(6, booking_status);
        preparedStatement.execute();
    }

    public void insert_belong(int id_booking, String id_room) throws SQLException {
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("insert into hotel_real.belong(ID_Booking,ID_Room) values (?,?)");
        preparedStatement.setInt(1, id_booking);
        preparedStatement.setString(2, id_room);
        preparedStatement.execute();
    }

    public void addMoreRoom(int id_booking, String id_room) throws SQLException {
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("insert into hotel_real.belong(ID_Booking,ID_Room) values (?,?)");
        preparedStatement.setInt(1, id_booking);
        preparedStatement.setString(2, id_room);
        preparedStatement.execute();
    }
    public ResultSet view_booking(int a) throws SQLException {
        CallableStatement callableStatement = DbUtil.getConnection().prepareCall("call view_booking(?)");
        callableStatement.setInt(1, a);
        ResultSet resultSet = callableStatement.executeQuery();
        return resultSet;
    }
    public List<Booking> view_booking_list() throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        PreparedStatement cst = DbUtil.getConnection().prepareStatement("select * from hotel_real.booking");
        ResultSet rss = cst.executeQuery();
        while (rss.next()) {
            bookingList.add(getListBooking(rss));
        }
        return bookingList;

    }
    public void update_status(int id) throws SQLException {
        PreparedStatement preparedStatement=DbUtil.getConnection().prepareStatement("update hotel_real.booking set booking.Booking_Status='1' where booking.ID=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
    public void delete_belong(int id) throws SQLException {
        PreparedStatement preparedStatement=DbUtil.getConnection().prepareStatement("delete from hotel_real.belong where belong.ID_Booking=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
    public  ResultSet getBelong(int id) throws SQLException {
        PreparedStatement preparedStatement=DbUtil.getConnection().prepareStatement("select ID_Room from hotel_real.belong where belong.ID_Booking=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet;
    }
    public Booking getListBooking(ResultSet rss) throws SQLException {
        Booking booking = new Booking();
        booking.setID(rss.getInt(1));
        booking.setName_User(rss.getString(2));
        booking.setCMND(rss.getString(3));
        booking.setDate_In(rss.getDate(4));
        booking.setDate_Out(rss.getDate(5));
        booking.setBooking_Date(rss.getDate(6));
        booking.setBooking_Status(rss.getString(7));
        return booking;
    }
}
