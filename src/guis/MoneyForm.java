package guis;

import constants.Constants;
import db.BoUser;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoneyForm extends JFrame {
    private final BoUser user;
    private JTextField TFInsertAmount;
    BankGUI bankMainPage;
    public MoneyForm(String title, BoUser user, BankGUI bankGui) {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(bankGui);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Constants.PRIMARY_COLOR);
        this.user = user;
        this.bankMainPage = bankGui;
        addGuiComponents();
    }
    private void addGuiComponents(){
        JLabel LBalance = new JLabel("Balance: " + user.getAmount());
        LBalance.setBounds(0, 50, 450, 25);
        LBalance.setForeground(Constants.TEXT_COLOR);
        LBalance.setHorizontalAlignment(SwingConstants.CENTER);
        LBalance.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(LBalance);

        JLabel LEnterAmount = new JLabel("Enter Amount:");
        LEnterAmount.setBounds(0, 100, 450, 25);
        LEnterAmount.setForeground(Constants.TEXT_COLOR);
        LEnterAmount.setHorizontalAlignment(SwingConstants.CENTER);
        LEnterAmount.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(LEnterAmount);

        TFInsertAmount = new JTextField("");
        TFInsertAmount.setBounds(10, 150, 415, 30);
        TFInsertAmount.setForeground(Constants.TEXT_COLOR);
        TFInsertAmount.setBackground(Constants.SECONDARY_COLOR);
        TFInsertAmount.setHorizontalAlignment(SwingConstants.CENTER);
        TFInsertAmount.setFont(new Font("Dialog", Font.PLAIN, 30));
        add(TFInsertAmount);

        JButton BAction = new JButton(getTitle());
        BAction.setBounds(10, 400, 415, 50);
        BAction.setFont(new Font("Dialog", Font.BOLD, 18));
        BAction.setHorizontalAlignment(SwingConstants.CENTER);
        BAction.setBackground(Constants.TEXT_COLOR);
        BAction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if(getTitle().equals("Deposit")) BAction.addActionListener(onDeposit);
        else if (getTitle().equals("Withdraw")) BAction.addActionListener(onWithDraw);
        add(BAction);
    }
    private final ActionListener onDeposit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!TFInsertAmount.getText().isEmpty()){
                try{
                    float amount = Float.parseFloat(TFInsertAmount.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(MoneyForm.this, "Input value has to be more than 0!");
                        return;
                    }
                    float newAmount = user.getAmount()+amount;
                    bankMainPage.LAmount.setText(Float.toString(newAmount));
                        user.setAmount(newAmount);
                        MyJDBC.recordTransaction(user.getId(), amount);
                    dispose();
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(MoneyForm.this, "Invalid input!");
                }
            }
        }
    };
    private final ActionListener onWithDraw = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!TFInsertAmount.getText().isEmpty()){
                try{
                    float amount = Float.parseFloat(TFInsertAmount.getText());
                    if (amount > user.getAmount()) {
                        JOptionPane.showMessageDialog(MoneyForm.this, "Value to withdraw too big for this account");
                        return;
                    }
                    float newAmount = user.getAmount() - amount;
                    user.setAmount(newAmount);
                    bankMainPage.LAmount.setText(Float.toString(newAmount));
                    MyJDBC.recordTransaction(user.getId(), amount * - 1);
                    dispose();
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(MoneyForm.this, "Invalid input!");
                }
            }
        }
    };
}
