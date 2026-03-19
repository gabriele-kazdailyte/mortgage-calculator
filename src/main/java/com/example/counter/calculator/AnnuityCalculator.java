package com.example.counter.calculator;

import com.example.counter.model.Deferral;
import com.example.counter.model.Loan;
import com.example.counter.model.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator extends LoanCalculator {
    private double interestRate;

    public AnnuityCalculator(Loan loan) {
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

        double monthlyPayment =
                amount * monthlyRate / (1 - Math.pow(1 + monthlyRate, -originalMonths));

        double balance = amount;
        LocalDate date = loan.getStartDate();
        int paymentNumber = 1;

        while (balance > 0.01) {
            boolean isDeferral = deferral != null &&
                    !date.isBefore(deferral.getStartDate()) &&
                    date.isBefore(deferral.getStartDate().plusMonths(deferral.getDurationMonths()));

            double interest = balance * monthlyRate;
            double normalPrincipal = monthlyPayment - interest;

            if (normalPrincipal < 0) {
                normalPrincipal = 0;
            }

            double currentPrincipal;
            double currentPayment;

            if (isDeferral) {
                double deferralPercent = deferral.getPercentage();
                deferralPercent = Math.max(0, Math.min(100, deferralPercent));

                currentPrincipal = normalPrincipal * (1 - deferralPercent / 100.0);

                if (currentPrincipal > balance) {
                    currentPrincipal = balance;
                }

                currentPayment = interest + currentPrincipal;
            }
            else {
                currentPrincipal = normalPrincipal;

                if (currentPrincipal > balance) {
                    currentPrincipal = balance;
                }

                currentPayment = interest + currentPrincipal;
            }

            balance = balance - currentPrincipal;
            paidAmount = paidAmount + currentPayment;

            if (balance < 0.01) {
                balance = 0.0;
            }

            payments.add(new Payment(paymentNumber, date, currentPayment, interest, currentPrincipal, balance, paidAmount));

            paymentNumber++;
            date = date.plusMonths(1);
        }

        return payments;
    }
}