package Persistance;

import java.util.Date;

public class Booking {
    private int ID;
    private String Name_User;
    private String CMND;
    private java.sql.Date Date_In;
    private java.sql.Date Date_Out;
    private java.sql.Date Booking_Date;
    private String Booking_Status;

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName_User() {
        return Name_User;
    }

    public void setName_User(String name_User) {
        Name_User = name_User;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public java.sql.Date getBooking_Date() {
        return Booking_Date;
    }

    public java.sql.Date getDate_In() {
        return Date_In;
    }

    public void setDate_In(java.sql.Date date_In) {
        Date_In = date_In;
    }

    public java.sql.Date getDate_Out() {
        return Date_Out;
    }

    public void setDate_Out(java.sql.Date date_Out) {
        Date_Out = date_Out;
    }

    public Date getBooking_Date(java.sql.Date date) {
        return Booking_Date;
    }

    public void setBooking_Date(java.sql.Date booking_Date) {
        Booking_Date = booking_Date;
    }

    public String getBooking_Status() {
        return Booking_Status;
    }

    public void setBooking_Status(String booking_Status) {
        Booking_Status = booking_Status;
    }
}
