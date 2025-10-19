package com.mediscan.dao;

import com.mediscan.model.Reminder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReminderDao {
    Reminder upsert(Reminder reminder);
    Optional<Reminder> findById(UUID reminderId);
    List<Reminder> listUpcoming(UUID userId, Instant from, Instant to);
}
