package com.mediscan.ui;

import com.mediscan.service.ServiceRegistry;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    public MainView() {
        setLayout(new BorderLayout());
        ServiceRegistry services = new ServiceRegistry();

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Upload", new UploadPanel(services));
        tabs.addTab("Reminders", new RemindersPanel(services));
        tabs.addTab("History", new HistoryPanel());
        tabs.addTab("Dashboard", new DashboardPanel());
        tabs.addTab("Profile", new ProfilePanel(services));
        add(tabs, BorderLayout.CENTER);
    }
}
