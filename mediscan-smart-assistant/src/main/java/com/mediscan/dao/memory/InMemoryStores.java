package com.mediscan.dao.memory;

import com.mediscan.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryStores {
    public static final Map<UUID, User> users = new ConcurrentHashMap<>();
    public static final Map<UUID, Profile> profiles = new ConcurrentHashMap<>();
    public static final Map<UUID, Prescription> prescriptions = new ConcurrentHashMap<>();
    public static final Map<UUID, PrescriptionItem> items = new ConcurrentHashMap<>();
    public static final Map<UUID, Reminder> reminders = new ConcurrentHashMap<>();
    public static final List<AdherenceRecord> adherence = Collections.synchronizedList(new ArrayList<>());

    public static List<Reminder> listUpcoming(UUID userId, Instant from, Instant to) {
        return reminders.values().stream()
                .filter(r -> r.getUserId().equals(userId))
                .filter(r -> !r.getScheduledTime().isBefore(from) && !r.getScheduledTime().isAfter(to))
                .sorted(Comparator.comparing(Reminder::getScheduledTime))
                .collect(Collectors.toList());
    }

    public static List<AdherenceRecord> listAdherence(UUID itemId, LocalDate from, LocalDate to) {
        synchronized (adherence) {
            return adherence.stream()
                    .filter(a -> a.getPrescriptionItemId().equals(itemId))
                    .filter(a -> (a.getDate().isEqual(from) || a.getDate().isAfter(from))
                            && (a.getDate().isEqual(to) || a.getDate().isBefore(to)))
                    .collect(Collectors.toList());
        }
    }
}
