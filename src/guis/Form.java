package guis;

import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    public Form(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 680);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
    }
}
