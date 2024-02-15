package guis;

import constants.Constants;
import db.BoUser;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGui extends Form {
    public LoginGui(){
        super("Login");
        addGuiComponents();
    }
    private void addGuiComponents(){

        JLabel LLogin = new JLabel("Login");
        LLogin.setBounds(0, 25, 520, 100);
        LLogin.setForeground(Constants.TEXT_COLOR);
        LLogin.setFont(new Font("Dialog", Font.BOLD, 40));
        LLogin.setHorizontalAlignment(SwingConstants.CENTER);
        add(LLogin);

        JLabel LUsername = new JLabel("Username:");
        LUsername.setBounds(30, 150, 400, 25);
        LUsername.setForeground(Constants.TEXT_COLOR);
        LUsername.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(LUsername);

        JTextField TFUsername = new JTextField("");
        TFUsername.setBounds(30, 185, 450, 55);
        TFUsername.setForeground(Constants.TEXT_COLOR);
        TFUsername.setBackground(Constants.SECONDARY_COLOR);
        TFUsername.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(TFUsername);

        JLabel LPassword = new JLabel("Password:");
        LPassword.setBounds(30, 335, 400, 25);
        LPassword.setForeground(Constants.TEXT_COLOR);
        LPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(LPassword);

        JTextField TFPassword = new JPasswordField("");
        TFPassword.setBounds(30, 365, 450, 55);
        TFPassword.setForeground(Constants.TEXT_COLOR);
        TFPassword.setBackground(Constants.SECONDARY_COLOR);
        TFPassword.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(TFPassword);

        JButton BLogin = new JButton ("Login");
        BLogin.setFont(new Font("Dialog", Font.BOLD, 18));
        BLogin.setHorizontalAlignment(SwingConstants.CENTER);
        BLogin.setBackground(Constants.TEXT_COLOR);
        BLogin.setBounds(125, 520, 250, 50);
        BLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               BoUser user = MyJDBC.validateLogin(TFUsername.getText(), TFPassword.getText());
               if(user != null) {
                   JOptionPane.showMessageDialog(LoginGui.this, "Login successful!");
                   dispose();
                   new BankGUI(user.getId()).setVisible(true);
               }
               else JOptionPane.showMessageDialog(LoginGui.this, "Login failed!");
            }
        });
        add(BLogin);
        JLabel LRegister = new JLabel("Not a user? Register here");
        LRegister.setHorizontalAlignment(SwingConstants.CENTER);
        LRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LRegister.setForeground(Constants.TEXT_COLOR);
        LRegister.setBounds(125, 600, 250, 30);
        LRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dispose();
               new RegisterGUI().setVisible(true);
            }
        });
        add(LRegister);
    }

}
