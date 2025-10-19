package com.mediscan.dao;

import com.mediscan.model.PrescriptionItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrescriptionItemDao {
    PrescriptionItem upsert(PrescriptionItem item);
    Optional<PrescriptionItem> findById(UUID itemId);
    List<PrescriptionItem> listByPrescription(UUID prescriptionId);
}
