package com.mediscan.service;

public class HealthService {
    public double calculateBmi(Double heightCm, Double weightKg) {
        if (heightCm == null || weightKg == null || heightCm <= 0 || weightKg <= 0) return Double.NaN;
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    public String bmiCategory(double bmi) {
        if (Double.isNaN(bmi)) return "Unknown";
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25.0) return "Normal";
        if (bmi < 30.0) return "Overweight";
        return "Obese";
    }
}
