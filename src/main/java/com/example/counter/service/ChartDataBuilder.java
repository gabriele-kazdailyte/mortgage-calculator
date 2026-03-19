package com.example.counter.service;

import com.example.counter.calculator.AnnuityCalculator;
import com.example.counter.calculator.LinearCalculator;
import com.example.counter.model.Loan;
import com.example.counter.model.Payment;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartDataBuilder {

    public static XYChart.Series<Number, Number> buildAnnuitySeries (Loan loan) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Anuiteto");

        List<Payment> annuityPayments = new AnnuityCalculator(loan).calculatePayments();

        for (Payment p : annuityPayments) {
            series.getData().add(new XYChart.Data<>(p.getNumber(), p.getPayment()));
        }
        return series;
    }

    public static XYChart.Series<Number, Number> buildLinnearSeries (Loan loan) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Linijinis");

        List<Payment> linearPayments = new LinearCalculator(loan).calculatePayments();

        for (Payment p : linearPayments) {
            series.getData().add(new XYChart.Data<>(p.getNumber(), p.getPayment()));
        }
        return series;
    }

}
