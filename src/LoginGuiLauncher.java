import constants.CommonConstants;
import db.MyJDBC;
import guis.BankGUI;
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
                    Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
                    MyJDBC.setConnection(connection);
                    new BankGUI(1).setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //new RegisterGUI().setVisible(true);
                System.out.println(MyJDBC.validateLogin("adamr121", "dupa"));
            }
        });
    }
}
