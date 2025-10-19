package com.mediscan.dao;

import com.mediscan.model.AdherenceRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AdherenceDao {
    AdherenceRecord insert(AdherenceRecord record);
    List<AdherenceRecord> listByItemAndRange(UUID itemId, LocalDate from, LocalDate to);
}
