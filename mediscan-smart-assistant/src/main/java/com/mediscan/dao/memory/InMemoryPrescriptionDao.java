package com.mediscan.dao.memory;

import com.mediscan.dao.PrescriptionDao;
import com.mediscan.model.Prescription;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPrescriptionDao implements PrescriptionDao {
    @Override
    public Prescription upsert(Prescription prescription) {
        InMemoryStores.prescriptions.put(prescription.getPrescriptionId(), prescription);
        return prescription;
    }

    @Override
    public Optional<Prescription> findById(UUID prescriptionId) {
        return Optional.ofNullable(InMemoryStores.prescriptions.get(prescriptionId));
    }

    @Override
    public List<Prescription> listByUser(UUID userId) {
        return InMemoryStores.prescriptions.values().stream()
                .filter(p -> p.getUserId().equals(userId))
                .sorted(Comparator.comparing(Prescription::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
