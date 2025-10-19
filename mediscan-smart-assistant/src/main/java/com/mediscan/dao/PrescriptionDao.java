package com.mediscan.dao;

import com.mediscan.model.Prescription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrescriptionDao {
    Prescription upsert(Prescription prescription);
    Optional<Prescription> findById(UUID prescriptionId);
    List<Prescription> listByUser(UUID userId);
}
