package guis;

import constants.Constants;
import db.BoUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class BankGUI extends Form {
    private final BoUser user;
    public JLabel LAmount;
    private final ActionListener onDeposit = e -> deposit();
    private final ActionListener onWithdraw = e -> withdraw();
    private final ActionListener onLogout = e -> logout();

    public BankGUI(int p_user_id){
        super("Bank");
        user = new BoUser(p_user_id);
        addGuiComponents();
    }
//    private BankGUI(){
//        super("Bank");
//    }
    private void addGuiComponents(){
        JLabel LBilans = new JLabel("Bilance:");
        LBilans.setBounds(200, 50, 400, 25);
        LBilans.setForeground(Constants.TEXT_COLOR);
        LBilans.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(LBilans);

        LAmount = new JLabel(String.format("%.2f", user.getAmount()) + " zl");
        LAmount.setBounds(180, 90, 400, 25);
        LAmount.setForeground(Constants.TEXT_COLOR);
        LAmount.setFont(new Font("Dialog", Font.PLAIN, 30));
        add(LAmount);

        JButton BHistory = new JButton("History");
        BHistory.setFont(new Font("Dialog", Font.BOLD, 18));
        BHistory.setHorizontalAlignment(SwingConstants.CENTER);
        BHistory.setBackground(Constants.TEXT_COLOR);
        BHistory.setBounds(125, 360, 250, 50);
        BHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BHistory.addActionListener((e) ->{
            new HistoryGUI(user, this).setVisible(true);
        });
        add(BHistory);

        JButton BDeposit = new JButton("Deposit");
        BDeposit.setFont(new Font("Dialog", Font.BOLD, 18));
        BDeposit.setHorizontalAlignment(SwingConstants.CENTER);
        BDeposit.setBackground(Constants.TEXT_COLOR);
        BDeposit.setBounds(125, 420, 250, 50);
        BDeposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BDeposit.addActionListener(onDeposit);
        add(BDeposit);

        JButton BWithdraw = new JButton("Withdraw");
        BWithdraw.setFont(new Font("Dialog", Font.BOLD, 18));
        BWithdraw.setHorizontalAlignment(SwingConstants.CENTER);
        BWithdraw.setBackground(Constants.TEXT_COLOR);
        BWithdraw.setBounds(125, 480, 250, 50);
        BWithdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BWithdraw.addActionListener(onWithdraw);
        add(BWithdraw);

        JButton BLogout = new JButton("Logout");
        BLogout.setFont(new Font("Dialog", Font.BOLD, 18));
        BLogout.setHorizontalAlignment(SwingConstants.CENTER);
        BLogout.setBackground(Constants.TEXT_COLOR);
        BLogout.setBounds(125, 540, 250, 50);
        BLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BLogout.addActionListener(onLogout);
        add(BLogout);
    }
    private void deposit(){
        new MoneyForm("Deposit", user, this).setVisible(true);
    }
    private void withdraw(){
        new MoneyForm("Withdraw", user, this).setVisible(true);
    }

    private void logout() {
        new LoginGui().setVisible(true);
        dispose();
    }

}
