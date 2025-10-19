package com.mediscan.ui;

import com.mediscan.model.Reminder;
import com.mediscan.service.ServiceRegistry;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.UUID;

public class RemindersPanel extends JPanel {
    private final ServiceRegistry services;

    public RemindersPanel(ServiceRegistry services) {
        this.services = services;
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton demo = new JButton("Schedule Demo Reminder (10s)");
        top.add(demo);
        add(top, BorderLayout.NORTH);
        add(new JLabel("Reminders list and schedule controls"), BorderLayout.CENTER);

        demo.addActionListener(e -> {
            UUID userId = UUID.randomUUID();
            UUID itemId = UUID.randomUUID();
            Reminder r = Reminder.createNew(userId, itemId, Instant.now().plusSeconds(10));
            services.reminderService.schedule(r);
            JOptionPane.showMessageDialog(this, "Demo reminder scheduled in 10 seconds.");
        });
    }

    public RemindersPanel() {
        this(new ServiceRegistry());
    }
}
