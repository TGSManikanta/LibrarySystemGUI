package com.mediscan.service;

import com.mediscan.config.AppConfig;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class OcrService {
    public String extractText(File file) throws IOException {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".pdf")) {
            return extractPdfText(file);
        }
        return extractImageText(file);
    }

    private String extractPdfText(File file) throws IOException {
        try (PDDocument doc = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc);
        }
    }

    private String extractImageText(File file) throws IOException {
        Tesseract tesseract = new Tesseract();
        String dataPath = AppConfig.get("tesseract.datapath", "");
        if (!dataPath.isEmpty()) {
            tesseract.setDatapath(dataPath);
        }
        String lang = AppConfig.get("tesseract.lang", "eng");
        tesseract.setLanguage(lang);
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            throw new IOException("OCR failed: " + e.getMessage(), e);
        }
    }
}
