package com.mediscan.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Prescription {
    private final UUID prescriptionId;
    private final UUID userId;
    private String sourceFilePath; // PDF/Image path
    private String ocrText;        // Raw OCR text
    private final List<PrescriptionItem> items;
    private final Instant createdAt;

    public Prescription(UUID prescriptionId, UUID userId) {
        this.prescriptionId = prescriptionId;
        this.userId = userId;
        this.items = new ArrayList<>();
        this.createdAt = Instant.now();
    }

    public static Prescription createNew(UUID userId) {
        return new Prescription(UUID.randomUUID(), userId);
    }

    public UUID getPrescriptionId() { return prescriptionId; }
    public UUID getUserId() { return userId; }
    public String getSourceFilePath() { return sourceFilePath; }
    public void setSourceFilePath(String sourceFilePath) { this.sourceFilePath = sourceFilePath; }
    public String getOcrText() { return ocrText; }
    public void setOcrText(String ocrText) { this.ocrText = ocrText; }
    public List<PrescriptionItem> getItems() { return items; }
    public Instant getCreatedAt() { return createdAt; }
}
