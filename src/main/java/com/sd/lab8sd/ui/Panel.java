package com.sd.lab8sd.ui;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
