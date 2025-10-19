package com.mediscan.service;

import com.mediscan.model.Prescription;
import com.mediscan.model.PrescriptionItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingService {
    private static final Pattern SCHEDULE_PATTERN = Pattern.compile("(1-0-1|0-1-1|1-1-1|OD|HS|BD|TDS)", Pattern.CASE_INSENSITIVE);
    private static final Pattern STRENGTH_PATTERN = Pattern.compile("(\\d+\\s?(mg|mcg|g|ml))", Pattern.CASE_INSENSITIVE);

    public List<PrescriptionItem> parseItems(UUID prescriptionId, String ocrText) {
        List<PrescriptionItem> items = new ArrayList<>();
        if (ocrText == null || ocrText.isBlank()) return items;

        String[] lines = ocrText.split("\\R");
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            Matcher scheduleMatch = SCHEDULE_PATTERN.matcher(trimmed);
            Matcher strengthMatch = STRENGTH_PATTERN.matcher(trimmed);

            if (scheduleMatch.find()) {
                PrescriptionItem item = PrescriptionItem.createNew(prescriptionId);
                item.setSchedule(scheduleMatch.group(1).toUpperCase());
                if (strengthMatch.find()) {
                    item.setStrength(strengthMatch.group(1));
                }
                // naive drug name as first token(s) before schedule
                String name = trimmed.substring(0, scheduleMatch.start()).trim();
                if (name.isEmpty()) name = "Medicine";
                item.setDrugName(name.replaceAll("[^A-Za-z0-9\\s]", "").trim());
                items.add(item);
            }
        }
        if (items.isEmpty()) {
            // fallback single item if nothing matched but text exists
            PrescriptionItem item = PrescriptionItem.createNew(prescriptionId);
            item.setDrugName("Medicine");
            item.setSchedule("OD");
            items.add(item);
        }
        return items;
    }
}
