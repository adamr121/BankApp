package guis;

import constants.Constants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterGUI extends Form{
    public RegisterGUI(){
        super("Register");
        addGuiComponents();
    }
    private void addGuiComponents(){
        JLabel LLogin = new JLabel("Register");
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

        TFUsername = new JTextField("");
        TFUsername.setBounds(30, 185, 450, 55);
        TFUsername.setForeground(Constants.TEXT_COLOR);
        TFUsername.setBackground(Constants.SECONDARY_COLOR);
        TFUsername.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(TFUsername);

        JLabel LPassword = new JLabel("Password:");
        LPassword.setBounds(30, 245, 400, 25);
        LPassword.setForeground(Constants.TEXT_COLOR);
        LPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(LPassword);

        TFPassword = new JPasswordField("");
        TFPassword.setBounds(30, 275, 450, 55);
        TFPassword.setForeground(Constants.TEXT_COLOR);
        TFPassword.setBackground(Constants.SECONDARY_COLOR);
        TFPassword.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(TFPassword);

        JLabel LPasswordAgain = new JLabel("Re-enter password:");
        LPasswordAgain.setBounds(30, 335, 400, 25);
        LPasswordAgain.setForeground(Constants.TEXT_COLOR);
        LPasswordAgain.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(LPasswordAgain);

        TFPasswordAgain = new JPasswordField("");
        TFPasswordAgain.setBounds(30, 365, 450, 55);
        TFPasswordAgain.setForeground(Constants.TEXT_COLOR);
        TFPasswordAgain.setBackground(Constants.SECONDARY_COLOR);
        TFPasswordAgain.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(TFPasswordAgain);

        JButton BRegister = new JButton ("Register");
        BRegister.setFont(new Font("Dialog", Font.BOLD, 18));
        BRegister.setHorizontalAlignment(SwingConstants.CENTER);
        BRegister.setBackground(Constants.TEXT_COLOR);
        BRegister.setBounds(125, 520, 250, 50);
        BRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BRegister.addActionListener(e -> onRegister());
        add(BRegister);

        JLabel LLogin2 = new JLabel("Have an account? Login here");
        LLogin2.setHorizontalAlignment(SwingConstants.CENTER);
        LLogin2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LLogin2.setForeground(Constants.TEXT_COLOR);
        LLogin2.setBounds(125, 600, 250, 30);
        LLogin2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginGui().setVisible(true);
            }
        });
        add(LLogin2);
    }
    private boolean validateUserInput(String username, String password, String repassword){
        if(username.isEmpty() && password.isEmpty() && repassword.isEmpty()) return false;
        if(username.length() < 6) return false;
        if(!password.equals(repassword)) return false;

        return true;
    }
    private JTextField TFPasswordAgain;
    private JTextField TFPassword;
    private JTextField TFUsername;
    private void onRegister(){
        String username = TFUsername.getText();
        String password = TFPassword.getText();
        String repassword = TFPasswordAgain.getText();

        if(validateUserInput(username, password, repassword)){
            if( MyJDBC.register(username, password)){
                dispose();
                LoginGui loginGui = new LoginGui();
                loginGui.setVisible(true);
                JOptionPane.showMessageDialog(loginGui, "Registered account successfully!");
            }
            else{
                JOptionPane.showMessageDialog(getContentPane(), "Username already taken");
            }
        }
        else{
            JOptionPane.showMessageDialog(getContentPane(), "Inavlid data!");
        }
    }
}
