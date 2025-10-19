package com.mediscan.model;

import java.time.LocalDate;
import java.util.UUID;

public class AdherenceRecord {
    private final UUID adherenceId;
    private final UUID userId;
    private final UUID prescriptionItemId;
    private final LocalDate date;
    private final boolean taken;

    public AdherenceRecord(UUID adherenceId, UUID userId, UUID prescriptionItemId, LocalDate date, boolean taken) {
        this.adherenceId = adherenceId;
        this.userId = userId;
        this.prescriptionItemId = prescriptionItemId;
        this.date = date;
        this.taken = taken;
    }

    public static AdherenceRecord createNew(UUID userId, UUID itemId, LocalDate date, boolean taken) {
        return new AdherenceRecord(UUID.randomUUID(), userId, itemId, date, taken);
        
    }

    public UUID getAdherenceId() { return adherenceId; }
    public UUID getUserId() { return userId; }
    public UUID getPrescriptionItemId() { return prescriptionItemId; }
    public LocalDate getDate() { return date; }
    public boolean isTaken() { return taken; }
}
