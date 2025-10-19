package com.mediscan.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class EmailService {
    public boolean sendInvite(String smtpHost, int smtpPort, String username, String password,
                              String toEmail, String subject, String description,
                              Instant start, Instant end) {
        try {
            String ics = buildIcs(subject, description, start, end);
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", String.valueOf(smtpPort));
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setSentDate(new Date());

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent("Please find calendar invite attached.", "text/plain; charset=UTF-8");

            MimeBodyPart calendarPart = new MimeBodyPart();
            calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
            calendarPart.setContent(ics, "text/calendar;method=REQUEST;charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(calendarPart);
            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public File exportIcs(File targetFile, String summary, String description, Instant start, Instant end) throws IOException {
        String ics = buildIcs(summary, description, start, end);
        try (FileWriter fw = new FileWriter(targetFile, StandardCharsets.UTF_8)) {
            fw.write(ics);
        }
        return targetFile;
    }

    private String buildIcs(String summary, String description, Instant start, Instant end) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").withZone(ZoneOffset.UTC);
        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCALENDAR\n");
        sb.append("VERSION:2.0\n");
        sb.append("PRODID:-//MediScan//EN\n");
        sb.append("METHOD:REQUEST\n");
        sb.append("BEGIN:VEVENT\n");
        sb.append("UID:").append(System.currentTimeMillis()).append("@mediscan\n");
        sb.append("DTSTAMP:").append(fmt.format(Instant.now())).append("\n");
        sb.append("DTSTART:").append(fmt.format(start)).append("\n");
        sb.append("DTEND:").append(fmt.format(end)).append("\n");
        sb.append("SUMMARY:").append(escape(summary)).append("\n");
        sb.append("DESCRIPTION:").append(escape(description)).append("\n");
        sb.append("END:VEVENT\n");
        sb.append("END:VCALENDAR\n");
        return sb.toString();
    }

    private String escape(String s) {
        return s == null ? "" : s.replace("\\", "\\\\").replace(";", "\\;").replace(",", "\\,").replace("\n", "\\n");
    }
}
