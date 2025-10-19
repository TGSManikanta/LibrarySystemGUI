package com.mediscan.ui;

import com.mediscan.service.HealthService;
import com.mediscan.service.ServiceRegistry;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private final ServiceRegistry services;

    public ProfilePanel(ServiceRegistry services) {
        this.services = services;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.anchor = GridBagConstraints.WEST;

        JTextField heightField = new JTextField(10);
        JTextField weightField = new JTextField(10);
        JLabel result = new JLabel("BMI: -  Category: -");

        int r = 0;
        gc.gridx = 0; gc.gridy = r; form.add(new JLabel("Height (cm):"), gc);
        gc.gridx = 1; gc.gridy = r; form.add(heightField, gc); r++;
        gc.gridx = 0; gc.gridy = r; form.add(new JLabel("Weight (kg):"), gc);
        gc.gridx = 1; gc.gridy = r; form.add(weightField, gc); r++;

        JButton calc = new JButton("Calculate BMI");
        gc.gridx = 0; gc.gridy = r; gc.gridwidth = 2; form.add(calc, gc); r++;
        gc.gridx = 0; gc.gridy = r; gc.gridwidth = 2; form.add(result, gc);

        add(form, BorderLayout.NORTH);

        calc.addActionListener(e -> {
            try {
                Double h = Double.valueOf(heightField.getText());
                Double w = Double.valueOf(weightField.getText());
                double bmi = services.healthService.calculateBmi(h, w);
                String cat = services.healthService.bmiCategory(bmi);
                result.setText(String.format("BMI: %.1f  Category: %s", bmi, cat));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public ProfilePanel() {
        this(new ServiceRegistry());
    }
}
