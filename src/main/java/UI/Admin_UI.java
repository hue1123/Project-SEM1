package UI;

import BL.Booking_BL;
import BL.Room_BL;
import Persistance.Booking;
import Persistance.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin_UI {
    public static void Admin_UI() throws SQLException, ParseException {
        final MenuUI menuUI=new MenuUI();
        String input;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("                   ****                     ");
        System.out.println("===========Welcome Michelin Hotel===========");
        System.out.println("|1.Manage Booking                          |");
        System.out.println("|2.Manage Room                             |");
        System.out.println("|3.Back to Menu                            |");
        System.out.println("============================================");
        System.out.println("Enter your choice: ");
        input = scanner.nextLine();
        switch (input) {
            case "1":
                Manage_Booking();
                break;
            case "2":
                Manage_Room();
                break;
            case "3":
                menuUI.Menu();
                break;
            default:
                System.out.println("Enter wrong!");
                Admin_UI();
                break;
        }
    }
    public static void Manage_Booking() throws SQLException, ParseException {
        String input;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("                   ****                     ");
        System.out.println("===========Welcome Michelin Hotel===========");
        System.out.println("|1.View List Booking                       |");
        System.out.println("|2.Update Status                           |");
        System.out.println("|3.Back to Menu                            |");
        System.out.println("============================================");
        System.out.println("Enter your choice: ");
        input = scanner.nextLine();
        switch (input) {
            case "1":
                view_list_booking();
                break;
            case "2":
                update_status_booking();
                break;
            case "3":
                Admin_UI();
                break;
            default:
                System.out.println("Enter wrong!");
                Manage_Booking();
                break;
        }
    }
    public static void view_list_booking() throws SQLException {
        final Booking_BL booking_bl=new Booking_BL();
        final List<Booking>bookingList=booking_bl.view_booking_list();
        System.out.printf("|%-10s|%-20s|%-13s|%-15s|%-15s|%-15s|%-15s|\n","ID","Tên Khách Hàng","CMND","Ngày Đến","Ngày Đi","Ngày Đặt Phòng","Thanh Toán");
        for (final Booking booking:
             bookingList) {
            System.out.printf("|%-10d|%-20s|%-13s|%-15s|%-15s|%-15s|%-15s|\n",booking.getID(),booking.getName_User(),booking.getCMND(),booking.getDate_In(),booking.getDate_Out(),booking.getBooking_Date(),booking.getBooking_Status());
        }
    }
    public static void update_status_booking() throws SQLException {
        int input;
        final Scanner scanner=new Scanner(System.in);
        final Booking_BL booking_bl=new Booking_BL();
        final Room_BL room_bl=new Room_BL();
        ResultSet resultSet;
        System.out.println("********************************************************");
        view_list_booking();
        System.out.println("Enter ID Booking: ");
        input=scanner.nextInt();
        booking_bl.update_status(input);
        resultSet=booking_bl.getBelong(input);
        while(resultSet.next()){
            room_bl.update_availability1(resultSet.getString(1));
        }
        booking_bl.delete_belong(input);
        System.out.println("******Update Successful******");
    }
    public static void Manage_Room() throws SQLException, ParseException {
        String input,choice;
        Room room1=new Room();
        final Scanner scanner=new Scanner(System.in);
        List<Room> roomList=new ArrayList<>();
        final Room_BL room_bl=new Room_BL();
        roomList=room_bl.getAllRoom();
        System.out.printf("|%-10s|%-20s|%-10s|%-10s|%-10s|%-20s|\n","ID","Loại Phòng","Tầng","Giá","Trống","Trạng Thái");

        for (final Room room:
             roomList) {
            String a=room.getAvailability();
            if(a.equals("1")){
                a="Có";
            }
            else a="Không";
            String b=room.getStatus();
            if(b.equals("1")){
                b="Đang Sử Dụng";
            }
            else b="Đang Sửa";
            System.out.printf("|%-10s|%-20s|%-10d|%-10d|%-10s|%-20s|\n",room.getRoom_id(),room.getRoom_type(),room.getFloor(),room.getPrice(),a,b);
        }
        System.out.println("Enter ID Room: ");
        input=scanner.nextLine();
        room1=room_bl.getRoom_by_id(input);
        if(room1.getAvailability().equals("0")){
            System.out.println("Can not update because this room is full!");
            Manage_Room();
        }
        if(room1.getStatus().equals("1")){
            room_bl.update_status("0",input);
        }
        else room_bl.update_status("1",input);
        System.out.println("****************Update Successful********************");
        System.out.println("Do you want to continue? (Y/N): ");
        choice=scanner.nextLine();
        if(choice.equals("Y")||choice.equals("y")){
            Manage_Room();
        }
        else Admin_UI();
    }
}

