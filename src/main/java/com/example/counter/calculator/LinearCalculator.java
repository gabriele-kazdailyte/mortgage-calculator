package com.example.counter.calculator;

import com.example.counter.model.Deferral;
import com.example.counter.model.Loan;
import com.example.counter.model.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LinearCalculator extends LoanCalculator {
    private double interestRate;

    public LinearCalculator(Loan loan) {
        super(loan);
        this.interestRate = loan.getInterestRate() / 100.0 / 12.0;
    }

    @Override
    public List<Payment> calculatePayments() {
        List<Payment> payments = new ArrayList<>();

        double monthlyRate = this.interestRate;
        int originalMonths = loan.getTermMonths();
        double amount = loan.getAmount();
        Deferral deferral = loan.getDeferral();
        double paidAmount = 0;

        double principalPerMonth = amount / originalMonths;

        double balance = amount;
        LocalDate date = loan.getStartDate();
        int paymentNumber = 1;

        while (balance > 0.01) {
            boolean isDeferral = deferral != null &&
                    !date.isBefore(deferral.getStartDate()) &&
                    date.isBefore(deferral.getStartDate().plusMonths(deferral.getDurationMonths()));

            double interest = balance * monthlyRate;
            double currentPrincipal;

            if (isDeferral) {
                double deferralPercent = deferral.getPercentage();
                currentPrincipal = principalPerMonth * (1 - deferralPercent / 100.0);
            } else {
                currentPrincipal = principalPerMonth;
            }

            if (currentPrincipal > balance) {
                currentPrincipal = balance;
            }

            double monthlyPayment = currentPrincipal + interest;
            balance = balance - currentPrincipal;
            paidAmount = paidAmount + monthlyPayment;

            if (balance < 0.01) {
                balance = 0.0;
            }

            payments.add(new Payment(paymentNumber, date, monthlyPayment, interest, currentPrincipal, balance, paidAmount));

            paymentNumber++;
            date = date.plusMonths(1);
        }

        return payments;
    }

}