package com.mediscan.dao.memory;

import com.mediscan.dao.PrescriptionItemDao;
import com.mediscan.model.PrescriptionItem;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPrescriptionItemDao implements PrescriptionItemDao {
    @Override
    public PrescriptionItem upsert(PrescriptionItem item) {
        InMemoryStores.items.put(item.getItemId(), item);
        return item;
    }

    @Override
    public Optional<PrescriptionItem> findById(UUID itemId) {
        return Optional.ofNullable(InMemoryStores.items.get(itemId));
    }

    @Override
    public List<PrescriptionItem> listByPrescription(UUID prescriptionId) {
        return InMemoryStores.items.values().stream()
                .filter(i -> i.getPrescriptionId().equals(prescriptionId))
                .collect(Collectors.toList());
    }
}
