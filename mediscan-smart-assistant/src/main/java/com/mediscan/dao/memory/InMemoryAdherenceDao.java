package com.mediscan.dao.memory;

import com.mediscan.dao.AdherenceDao;
import com.mediscan.model.AdherenceRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InMemoryAdherenceDao implements AdherenceDao {
    @Override
    public AdherenceRecord insert(AdherenceRecord record) {
        InMemoryStores.adherence.add(record);
        return record;
    }

    @Override
    public List<AdherenceRecord> listByItemAndRange(UUID itemId, LocalDate from, LocalDate to) {
        return InMemoryStores.listAdherence(itemId, from, to);
    }
}
