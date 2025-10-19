package com.mediscan.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class PrescriptionItem {
    private final UUID itemId;
    private final UUID prescriptionId;
    private String drugName;         // Normalized generic name
    private String brandName;        // Optional brand
    private String strength;         // e.g., 500 mg
    private String schedule;         // e.g., 1-0-1, OD, HS
    private Integer durationDays;    // e.g., 5 days
    private BigDecimal unitPrice;    // optional
    private LocalDate startDate;     // optional

    public PrescriptionItem(UUID itemId, UUID prescriptionId) {
        this.itemId = itemId;
        this.prescriptionId = prescriptionId;
    }

    public static PrescriptionItem createNew(UUID prescriptionId) {
        return new PrescriptionItem(UUID.randomUUID(), prescriptionId);
    }

    public UUID getItemId() { return itemId; }
    public UUID getPrescriptionId() { return prescriptionId; }
    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }
    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}
