package com.mediscan.model;

import java.time.LocalDate;
import java.util.UUID;

public class Profile {
    private final UUID profileId;
    private final UUID userId;
    private LocalDate dateOfBirth;
    private Double heightCm;
    private Double weightKg;

    public Profile(UUID profileId, UUID userId, LocalDate dateOfBirth, Double heightCm, Double weightKg) {
        this.profileId = profileId;
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
    }

    public static Profile createNew(UUID userId) {
        return new Profile(UUID.randomUUID(), userId, null, null, null);
    }

    public UUID getProfileId() { return profileId; }
    public UUID getUserId() { return userId; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public Double getHeightCm() { return heightCm; }
    public Double getWeightKg() { return weightKg; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setHeightCm(Double heightCm) { this.heightCm = heightCm; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }
}
