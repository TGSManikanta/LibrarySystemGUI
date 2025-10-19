package com.mediscan.ui;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    public HistoryPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Prescription history and search"), BorderLayout.CENTER);
    }
}
