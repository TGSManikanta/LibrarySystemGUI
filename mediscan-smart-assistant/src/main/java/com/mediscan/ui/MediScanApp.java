package com.mediscan.ui;

import javax.swing.*;
import java.awt.*;

public class MediScanApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            JFrame frame = new JFrame("MediScan – Smart Prescription Assistant");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(1100, 720));
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new MainView());
            frame.setVisible(true);
        });
    }
}
