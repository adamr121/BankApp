package guis;

import constants.CommonConstants;
import db.BoUser;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BankGUI extends Form {
    private final BoUser user;
    private JLabel LAmount;
    private final ActionListener onDeposit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deposit();
        }
    };
    private final ActionListener onWithdraw = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            withdraw();
        }
    };
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
        LBilans.setForeground(CommonConstants.TEXT_COLOR);
        LBilans.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(LBilans);

        LAmount = new JLabel(String.valueOf(user.getAmount())+" zl");
        LAmount.setBounds(180, 90, 400, 25);
        LAmount.setForeground(CommonConstants.TEXT_COLOR);
        LAmount.setFont(new Font("Dialog", Font.PLAIN, 30));
        add(LAmount);

        JButton BDeposit = new JButton("Deposit");
        BDeposit.setFont(new Font("Dialog", Font.BOLD, 18));
        BDeposit.setHorizontalAlignment(SwingConstants.CENTER);
        BDeposit.setBackground(CommonConstants.TEXT_COLOR);
        BDeposit.setBounds(125, 420, 250, 50);
        BDeposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BDeposit.addActionListener(onDeposit);
        add(BDeposit);

        JButton BWithdraw = new JButton("Withdraw");
        BWithdraw.setFont(new Font("Dialog", Font.BOLD, 18));
        BWithdraw.setHorizontalAlignment(SwingConstants.CENTER);
        BWithdraw.setBackground(CommonConstants.TEXT_COLOR);
        BWithdraw.setBounds(125, 480, 250, 50);
        BWithdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BWithdraw.addActionListener(onWithdraw);
        add(BWithdraw);

        JButton BHistory = new JButton("History");
        BHistory.setFont(new Font("Dialog", Font.BOLD, 18));
        BHistory.setHorizontalAlignment(SwingConstants.CENTER);
        BHistory.setBackground(CommonConstants.TEXT_COLOR);
        BHistory.setBounds(125, 540, 250, 50);
        BHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BHistory.addActionListener(onWithdraw);
        add(BHistory);
    }
    private void deposit(){
        MoneyForm moneyf = new MoneyForm("Deposit", user);
        moneyf.setVisible(true);
        while(moneyf.isActive()){}
        LAmount.setText(String.valueOf(user.getAmount()));

    }
    private void withdraw(){

    }
}
