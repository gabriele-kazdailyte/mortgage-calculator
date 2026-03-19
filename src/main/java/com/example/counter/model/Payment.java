package com.example.counter.model;

import java.time.LocalDate;

public class Payment {
    private int number;
    private LocalDate date;
    private double payment;
    private double interest;
    private double principal;
    private double balance;
    private double paid;

    public Payment(int number, LocalDate date, double payment,
                double interest, double principal, double balance, double paid) {
        this.number = number;
        this.date = date;
        this.payment = payment;
        this.interest = interest;
        this.principal = principal;
        this.balance = balance;
        this.paid = paid;
    }

    public int getNumber() { return number; }
    public LocalDate getDate() { return date; }
    public double getPayment(){ return payment; }
    public double getInterest(){ return interest; }
    public double getPrincipal(){ return principal; }
    public double getBalance(){ return balance; }
    public double getPaid(){ return paid; }

    public String getPaymentStr() { return String.format("%.2f €", payment); }
    public String getInterestStr() { return String.format("%.2f €", interest); }
    public String getPrincipalStr() { return String.format("%.2f €", principal); }
    public String getBalanceStr() { return String.format("%.2f €", balance); }
    public String getPaidStr() { return String.format("%.2f €", paid); }
}
