package com.example.counter.calculator;

import com.example.counter.model.Loan;
import com.example.counter.model.Payment;

import java.util.List;

public abstract class LoanCalculator {
    protected Loan loan;
    protected double interestRate;

    public LoanCalculator(Loan loan) {
        this.loan = loan;
        this.interestRate = loan.getInterestRate();
    }

    public abstract List<Payment> calculatePayments();

}