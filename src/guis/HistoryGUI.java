package guis;

import constants.Constants;
import db.BoTransaction;
import db.BoUser;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HistoryGUI extends JFrame {
    private BoUser user;
    public HistoryGUI(BoUser user, BankGUI parentDialog){
        super("Transaction history");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(parentDialog);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Constants.PRIMARY_COLOR);
        this.user = user;

        addComponents();
    }
    private void addComponents(){
        JLabel LTitle = new JLabel("Transaction history");
        LTitle.setForeground(Constants.TEXT_COLOR);
        LTitle.setHorizontalAlignment(SwingConstants.CENTER);
        LTitle.setBounds(0, 30, 450, 30);
        LTitle.setFont(new Font("Dialog", Font.BOLD, 30));
        add(LTitle);

        GridLayout recordsTable = new GridLayout(6, 2);
        JPanel PHistoryRecords = new JPanel(recordsTable);
        PHistoryRecords.setBackground(Constants.PRIMARY_COLOR);
        PHistoryRecords.setBounds(70, 80, 300, 350);

        JLabel LAmount = new JLabel("Amount");
        LAmount.setFont(new Font("Dialog", Font.BOLD, 20));
        LAmount.setHorizontalAlignment(SwingConstants.CENTER);
        LAmount.setForeground(Constants.TEXT_COLOR);
        PHistoryRecords.add(LAmount);

        JLabel LDate = new JLabel("Date");
        LDate.setFont(new Font("Dialog", Font.BOLD, 20));
        LDate.setHorizontalAlignment(SwingConstants.CENTER);
        LDate.setForeground(Constants.TEXT_COLOR);
        PHistoryRecords.add(LDate);

        ArrayList<BoTransaction> transactions = MyJDBC.getTransactionsByUser(user.getId(), 5);
        assert transactions != null;
        for(int i = 0; i < 5; i++){
            JLabel amount = new JLabel("");
            amount.setHorizontalAlignment(SwingConstants.CENTER);
            amount.setFont(new Font("Dialog", Font.BOLD, 20));
            amount.setForeground(Constants.TEXT_COLOR);

            JLabel date = new JLabel("");
            date.setHorizontalAlignment(SwingConstants.CENTER);
            date.setFont(new Font("Dialog", Font.BOLD, 15));
            date.setForeground(Constants.TEXT_COLOR);

            if( i < transactions.size()){
                amount.setText(Float.toString(transactions.get(i).getAmount()));
                date.setText(transactions.get(i).getDate());
            }
            PHistoryRecords.add(amount);
            PHistoryRecords.add(date);
        }
        add(PHistoryRecords);
    }
}
