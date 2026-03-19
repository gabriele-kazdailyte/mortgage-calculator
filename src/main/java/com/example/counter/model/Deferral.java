package com.example.counter.model;

import java.time.LocalDate;

public class Deferral {
    private LocalDate startDate;
    private int durationMonths;
    private double percentage;

    public Deferral(LocalDate startDate, int durationMonths, double percentage) {
        this.startDate = startDate;
        this.durationMonths = durationMonths;
        this.percentage = percentage;
    }

    public LocalDate getStartDate() { return startDate; }
    public int getDurationMonths() { return durationMonths; }
    public double getPercentage() { return percentage; }
}
