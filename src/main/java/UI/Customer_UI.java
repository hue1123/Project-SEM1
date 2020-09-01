package UI;

import BL.Booking_BL;
import BL.Room_BL;
import Persistance.Booking;
import Persistance.Room;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer_UI {
    static int a;

    public static void Customer_UI() throws SQLException, ParseException {
        MenuUI menuUI = new MenuUI();
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("                   ****                    ");
        System.out.println("===========Welcome Michelin Hotel===========");
        System.out.println("|1.Booking                                 |");
        System.out.println("|2.View Booking                            |");
        System.out.println("|3.Back to Menu                            |");
        System.out.println("============================================");
        System.out.println("Enter your choice: ");
        input = scanner.nextLine();
        switch (input) {
            case "1":
                Booking();
                break;
            case "2":
                view_booking(a);
                break;
            case "3":
                menuUI.Menu();
                break;
            default:
                System.out.println("Enter wrong!");
                Customer_UI();
                break;
        }
    }

    public static void Booking() throws SQLException, ParseException {
        Booking booking = new Booking();
        Room_BL roomBL = new Room_BL();
        Booking_BL booking_bl = new Booking_BL();
        Scanner scanner = new Scanner(System.in);
        java.sql.Date date_in, date_out, date_now;
        String input, choice, name, cmnd;
        System.out.println("=================Room List===============");
        List<Room> roomList = roomBL.view_room();
        for (Room room : roomList) {
            System.out.printf("|%-10s|%-30s|%-10d|%-10d|%-5s|%-5s|\n", room.getRoom_id(), room.getRoom_type(), room.getFloor(), room.getPrice(), room.getAvailability(), room.getStatus());
        }
        System.out.println("=========================================");
        System.out.println("Enter ID room you want to book: ");
        input = scanner.nextLine();
        boolean check = Room_BL.check_id(input);
        if (check == true) {
            System.out.println("This room has been booked or not exist! Do you want to book another room? (Y/N): ");
            choice = scanner.nextLine();
            if (choice.equals("Y") || choice.equals("y")) {
                Booking();
            } else Customer_UI();
        }
        System.out.println("Enter your Name: ");
        name = scanner.nextLine();
        System.out.println("Enter your ID Card: ");
        cmnd = scanner.nextLine();
        date_in = returnDate_In();
        date_out = returnDate_Out();
        date_now = Date.valueOf(LocalDate.now());
        booking_bl.add(name, cmnd, date_in, date_out, date_now, "0");
        booking = booking_bl.getBooking(cmnd);
        a = booking.getID();
        booking_bl.insert_belong(booking.getID(), input);
        roomBL.update_Availability(input);
        System.out.println("*****Booking Successful!*****");
        System.out.println("Would you like book more room? (Y/N): ");
        String choice1 = scanner.nextLine();
        if (choice1.equals("Y") || choice1.equals("y")) {
            enterAdd(booking.getID());
        } else {
            Customer_UI();
        }
    }

    public static void enterAdd(int id) throws SQLException, ParseException {
        String input1, choice, choice2;
        boolean check;
        Scanner scanner = new Scanner(System.in);
        Room_BL room_bl = new Room_BL();
        System.out.println("Enter ID room you want to book: ");
        input1 = scanner.nextLine();
        check = Room_BL.check_id(input1);

        if (check == true) {
            System.out.println("This room has been booked or not exist! Do you want to book another room? (Y/N): ");
            choice = scanner.nextLine();
            if (choice.equals("Y") || choice.equals("y")) {
                enterAdd(id);
            } else Customer_UI();
        } else {
            new Booking_BL().addMoreRoom(id, input1);
            room_bl.update_Availability(input1);
            System.out.println("*****Booking Successful!*****");
            System.out.println("Would you like book more room? (Y/N): ");
            choice2 = scanner.nextLine();
            if (choice2.equals("Y") || choice2.equals("y")) {
                enterAdd(id);
            } else Customer_UI();
        }
    }

    private static java.sql.Date returnDate_In() {
        Date date = null;
        Scanner scanner = new Scanner(System.in);
        String report = "Nhập ngày đến (yyyy-MM-dd): ";
        try {
            System.out.println(report);
            date = Date.valueOf(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error");
            returnDate_In();
        }
        return date;
    }

    private static java.sql.Date returnDate_Out() {
        Date date = null;
        Scanner scanner = new Scanner(System.in);
        String report = "Nhập ngày đi (yyyy-MM-dd): ";
        try {
            System.out.println(report);
            date = Date.valueOf(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error");
            returnDate_Out();
        }
        return date;
    }

    public static void view_booking(int id_booking) throws SQLException {
        Booking_BL bookingBL = new Booking_BL();
        int price = 0;
        ResultSet resultSet = bookingBL.view_booking(id_booking);
        while(resultSet.next()) {
            System.out.println("Tên: " + resultSet.getString(2));
            System.out.println("CMND: " + resultSet.getString(3));
            System.out.println("Mã Đặt Phòng: " + resultSet.getInt(1));
            System.out.println("Ngày Nhận Phòng: " + resultSet.getDate(4));
            System.out.println("Ngày Trả Phòng: " + resultSet.getDate(5));
            System.out.println("Ngày Đặt Phòng: " + resultSet.getDate(6));
            if (resultSet.getString(7).equals("1")) {
                System.out.println("Trạng Thái Thanh Toán: Đã Thanh Toán");
            } else System.out.println("Trạng Thái Thanh Toán: Chưa Thanh Toán");
                System.out.println("Phòng: " +resultSet.getString(8));
                System.out.println("Loại: " + resultSet.getString(9));
                System.out.println("Tầng: " + resultSet.getString(10));
                price += resultSet.getInt(11);
            System.out.println("Tổng Tiền: " + price);
//        }
        }
    }
}


