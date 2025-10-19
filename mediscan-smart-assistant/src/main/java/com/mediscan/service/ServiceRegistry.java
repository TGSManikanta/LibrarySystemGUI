package com.mediscan.service;

import com.mediscan.dao.*;
import com.mediscan.dao.memory.*;

public class ServiceRegistry {
    public final UserDao userDao = new InMemoryUserDao();
    public final ProfileDao profileDao = new InMemoryProfileDao();
    public final PrescriptionDao prescriptionDao = new InMemoryPrescriptionDao();
    public final PrescriptionItemDao itemDao = new InMemoryPrescriptionItemDao();
    public final ReminderDao reminderDao = new InMemoryReminderDao();

    public final OcrService ocrService = new OcrService();
    public final ParsingService parsingService = new ParsingService();
    public final HealthService healthService = new HealthService();
    public final EmailService emailService = new EmailService();
    public final AnalyticsService analyticsService = new AnalyticsService(new InMemoryAdherenceDao());
    public final ReminderService reminderService = new ReminderService(reminderDao);
}
