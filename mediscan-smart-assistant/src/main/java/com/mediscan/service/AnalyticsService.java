package com.mediscan.service;

import com.mediscan.dao.AdherenceDao;
import com.mediscan.model.AdherenceRecord;

import java.time.LocalDate;
import java.util.List;

public class AnalyticsService {
    private final AdherenceDao adherenceDao;

    public AnalyticsService(AdherenceDao adherenceDao) {
        this.adherenceDao = adherenceDao;
    }

    public double adherencePercent(java.util.UUID itemId, LocalDate from, LocalDate to) {
        List<AdherenceRecord> records = adherenceDao.listByItemAndRange(itemId, from, to);
        if (records.isEmpty()) return 0.0;
        long taken = records.stream().filter(AdherenceRecord::isTaken).count();
        return (taken * 100.0) / records.size();
    }
}
