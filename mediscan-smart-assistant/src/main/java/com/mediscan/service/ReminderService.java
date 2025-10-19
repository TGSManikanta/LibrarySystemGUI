package com.mediscan.service;

import com.mediscan.dao.ReminderDao;
import com.mediscan.model.Reminder;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.swing.*;
import java.time.Instant;

public class ReminderService {
    private final ReminderDao reminderDao;
    private final Scheduler scheduler;

    public ReminderService(ReminderDao reminderDao) {
        this.reminderDao = reminderDao;
        try {
            this.scheduler = StdSchedulerFactory.getDefaultScheduler();
            this.scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public Reminder schedule(Reminder reminder) {
        reminderDao.upsert(reminder);
        try {
            JobDetail job = JobBuilder.newJob(ShowPopupJob.class)
                    .withIdentity(reminder.getReminderId().toString())
                    .usingJobData("reminderId", reminder.getReminderId().toString())
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger-" + reminder.getReminderId())
                    .startAt(java.util.Date.from(reminder.getScheduledTime()))
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            // ignore scheduling errors for now
        }
        return reminder;
    }

    public void shutdown() {
        try { scheduler.shutdown(); } catch (SchedulerException ignored) {}
    }

    public static class ShowPopupJob implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Medicine reminder", "MediScan", JOptionPane.INFORMATION_MESSAGE));
        }
    }
}
