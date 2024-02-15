package guis;

import constants.Constants;

import javax.swing.*;

public class Form extends JFrame {
    public Form(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 680);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        getContentPane().setBackground(Constants.PRIMARY_COLOR);
    }
}
