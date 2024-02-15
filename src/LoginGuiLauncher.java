import constants.Constants;
import db.MyJDBC;
import guis.LoginGui;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginGuiLauncher {
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new LoginGui().setVisible(true);

                try{
                    Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
                    MyJDBC.setConnection(connection);
                    new LoginGui().setVisible(true);
                } catch (SQLException e) {
                    System.out.println("Connection to database could not be established");
                }
            }
        });
    }
}
