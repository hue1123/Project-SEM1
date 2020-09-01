package UI;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class MenuUI {
    public void Menu() throws SQLException, ParseException {
        String input;
        Scanner scanner=new Scanner(System.in);
        Customer_UI customer_ui=new Customer_UI();
        Admin_UI admin_ui=new Admin_UI();
        System.out.println("                   ****                    ");
        System.out.println("===========Welcome Michelin Hotel==========");
        System.out.println("|1.Customer                               |");
        System.out.println("|2.Admin                                  |");
        System.out.println("===========================================");
        System.out.println("Enter your choice: ");
        input=scanner.nextLine();
        switch (input){
            case "1":
                 customer_ui.Customer_UI();
                break;
            case "2":
                admin_ui.Admin_UI();
                break;
            default:
                System.out.println("Enter wrong!");
                Menu();
                break;
        }

    }
}
