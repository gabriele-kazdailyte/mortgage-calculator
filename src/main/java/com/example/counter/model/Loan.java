package com.example.counter.model;

import java.time.LocalDate;

public class Loan {
    private double amount;
    private int termMonths;
    private double interestRate;
    private LocalDate startDate;
    private Deferral deferral;

    public Loan(double amount, int termMonths,
                double interestRate, LocalDate startDate, Deferral deferral) {
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.deferral = deferral;
    }

    public double getAmount() { return amount; }
    public int getTermMonths() { return termMonths; }
    public double getInterestRate() { return interestRate; }
    public LocalDate getStartDate() { return startDate; }
    public Deferral getDeferral() { return deferral; }

}
