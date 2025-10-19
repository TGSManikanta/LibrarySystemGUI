package com.mediscan.ui;

import com.mediscan.model.Prescription;
import com.mediscan.model.PrescriptionItem;
import com.mediscan.service.ServiceRegistry;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UploadPanel extends JPanel {
    private final JTextArea textArea;
    private final ServiceRegistry services;

    private File lastFile;
    private Prescription lastPrescription;

    public UploadPanel(ServiceRegistry services) {
        this.services = services;
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton uploadBtn = new JButton("Upload Prescription (PDF/Image)");
        JButton parseBtn = new JButton("Parse & Create Items");
        top.add(uploadBtn);
        top.add(parseBtn);
        add(top, BorderLayout.NORTH);

        textArea = new JTextArea("Recognized text will appear here...");
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        uploadBtn.addActionListener(e -> onUpload());
        parseBtn.addActionListener(e -> onParse());
    }

    public UploadPanel() {
        this(new ServiceRegistry());
    }

    private void onUpload() {
        JFileChooser chooser = new JFileChooser();
        int ret = chooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            lastFile = chooser.getSelectedFile();
            try {
                String text = services.ocrService.extractText(lastFile);
                textArea.setText(text);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "OCR failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onParse() {
        if (textArea.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "No text to parse", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (lastPrescription == null) {
            UUID userId = UUID.randomUUID(); // demo user id
            lastPrescription = Prescription.createNew(userId);
            lastPrescription.setSourceFilePath(lastFile != null ? lastFile.getAbsolutePath() : null);
        }
        lastPrescription.setOcrText(textArea.getText());
        List<PrescriptionItem> items = services.parsingService.parseItems(lastPrescription.getPrescriptionId(), textArea.getText());
        lastPrescription.getItems().addAll(items);
        services.prescriptionDao.upsert(lastPrescription);
        for (PrescriptionItem item : items) {
            services.itemDao.upsert(item);
        }
        JOptionPane.showMessageDialog(this, "Parsed items: " + items.size(), "Parse complete", JOptionPane.INFORMATION_MESSAGE);
    }
}
