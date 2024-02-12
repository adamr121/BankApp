package guis;

import constants.CommonConstants;
import db.BoUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoneyForm extends JFrame {
    private final BoUser user;
    public MoneyForm(String title, BoUser p_user) {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
        user = p_user;
        addGuiComponents();
    }
    private void addGuiComponents(){
        JLabel LBalance = new JLabel("Balance: " + user.getAmount());
        LBalance.setBounds(0, 50, 450, 25);
        LBalance.setForeground(CommonConstants.TEXT_COLOR);
        LBalance.setHorizontalAlignment(SwingConstants.CENTER);
        LBalance.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(LBalance);

        JLabel LEnterAmount = new JLabel("Enter Amount:");
        LEnterAmount.setBounds(0, 100, 450, 25);
        LEnterAmount.setForeground(CommonConstants.TEXT_COLOR);
        LEnterAmount.setHorizontalAlignment(SwingConstants.CENTER);
        LEnterAmount.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(LEnterAmount);

        JTextField TFInsertAmount = new JTextField("");
        TFInsertAmount.setBounds(10, 150, 415, 30);
        TFInsertAmount.setForeground(CommonConstants.TEXT_COLOR);
        TFInsertAmount.setBackground(CommonConstants.SECONDARY_COLOR);
        TFInsertAmount.setHorizontalAlignment(SwingConstants.CENTER);
        TFInsertAmount.setFont(new Font("Dialog", Font.PLAIN, 30));
        add(TFInsertAmount);

        JButton BAction = new JButton(getTitle());
        BAction.setBounds(10, 400, 415, 50);
        BAction.setFont(new Font("Dialog", Font.BOLD, 18));
        BAction.setHorizontalAlignment(SwingConstants.CENTER);
        BAction.setBackground(CommonConstants.TEXT_COLOR);
        BAction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!TFInsertAmount.getText().isEmpty()){
                    float amount = Float.parseFloat(TFInsertAmount.getText());
                    user.setAmount(user.getAmount()+amount);
                    dispose();
                }
            }
        });
        add(BAction);
    }
}
