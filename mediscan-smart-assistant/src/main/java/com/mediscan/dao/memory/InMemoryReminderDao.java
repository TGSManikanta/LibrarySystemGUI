package com.mediscan.dao.memory;

import com.mediscan.dao.ReminderDao;
import com.mediscan.model.Reminder;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryReminderDao implements ReminderDao {
    @Override
    public Reminder upsert(Reminder reminder) {
        InMemoryStores.reminders.put(reminder.getReminderId(), reminder);
        return reminder;
    }

    @Override
    public Optional<Reminder> findById(UUID reminderId) {
        return Optional.ofNullable(InMemoryStores.reminders.get(reminderId));
    }

    @Override
    public List<Reminder> listUpcoming(UUID userId, Instant from, Instant to) {
        return InMemoryStores.listUpcoming(userId, from, to).stream().collect(Collectors.toList());
    }
}
