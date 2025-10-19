package com.mediscan.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Analytics dashboard (BMI, adherence, cost)"), BorderLayout.CENTER);
    }
}
