package com.mediscan.model;

import java.time.Instant;
import java.util.UUID;

public class Reminder {
    private final UUID reminderId;
    private final UUID userId;
    private final UUID prescriptionItemId;
    private Instant scheduledTime;
    private boolean taken;

    public Reminder(UUID reminderId, UUID userId, UUID prescriptionItemId, Instant scheduledTime) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.prescriptionItemId = prescriptionItemId;
        this.scheduledTime = scheduledTime;
        this.taken = false;
    }

    public static Reminder createNew(UUID userId, UUID prescriptionItemId, Instant when) {
        return new Reminder(UUID.randomUUID(), userId, prescriptionItemId, when);
    }

    public UUID getReminderId() { return reminderId; }
    public UUID getUserId() { return userId; }
    public UUID getPrescriptionItemId() { return prescriptionItemId; }
    public Instant getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(Instant scheduledTime) { this.scheduledTime = scheduledTime; }
    public boolean isTaken() { return taken; }
    public void setTaken(boolean taken) { this.taken = taken; }
}
