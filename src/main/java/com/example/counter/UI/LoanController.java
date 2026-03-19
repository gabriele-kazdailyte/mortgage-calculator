package com.example.counter.UI;

import com.example.counter.calculator.AnnuityCalculator;
import com.example.counter.calculator.LinearCalculator;
import com.example.counter.calculator.LoanCalculator;
import com.example.counter.model.Deferral;
import com.example.counter.model.Loan;
import com.example.counter.model.Payment;
import com.example.counter.service.ChartDataBuilder;
import com.example.counter.service.PaymentFilter;
import com.example.counter.service.ReportGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class LoanController {

    private List<Payment>  payments;
    private List<Payment>  filteredList;
    private boolean filterChecked = false;

    @FXML
    private TextField amountField;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField interestField;

    @FXML
    private ComboBox<String> loanChoice;

    @FXML
    private TableView<Payment> paymentTable;

    @FXML
    private TableColumn<Payment, String> balanceCol;

    @FXML
    private TableColumn<Payment, LocalDate> dateCol;

    @FXML
    private TableColumn<Payment, String> interestCol;

    @FXML
    private TableColumn<Payment, Integer> nrCol;

    @FXML
    private TableColumn<Payment, String> paymentCol;

    @FXML
    private TableColumn<Payment, String> principalCol;

    @FXML
    private TableColumn<Payment, String> paidCol;

    @FXML
    private LineChart<Number, Number> paymentChart;

    @FXML
    private DatePicker startFilterDate;

    @FXML
    private DatePicker endFilterDate;

    @FXML
    private CheckBox deferralExists;

    @FXML
    private TextField deferralPercentage;

    @FXML
    private DatePicker deferralStartDate;

    @FXML
    private TextField deferralTerm;

    @FXML
    private TextField termMonths;

    @FXML
    private TextField termYears;

    @FXML
    private DatePicker startDate;

    @FXML
    private CheckBox filterExists;

    @FXML
    void calculate(ActionEvent event) {
        double amount = Double.parseDouble(amountField.getText());
        double interestRate = Double.parseDouble(interestField.getText());

        int years = Integer.parseInt(termYears.getText());
        int months = Integer.parseInt(termMonths.getText());


        if(startDate.getValue() == null) {
            resultLabel.setText("Pasirinkite teisingą termino pradžios datą");
            return;
        }
        LocalDate start = startDate.getValue();

        long termMonths = years * 12 + months;
        if(termMonths <= 0) {
            resultLabel.setText("Terminas turi būti bent vienas mėnuo");
            return;
        }


        // deferral
        Deferral deferral = null;

        if (deferralExists.isSelected() && deferralStartDate.getValue() != null &&
                !deferralTerm.getText().isEmpty() &&
                !deferralPercentage.getText().isEmpty()) {

            if(deferralStartDate.getValue().isBefore(start) ||
                    deferralStartDate.getValue().isAfter(start.plusMonths(termMonths)) ||
                    deferralStartDate.getValue().plusMonths(Integer.parseInt(deferralTerm.getText())).isAfter(start.plusMonths(termMonths))) {
                resultLabel.setText("Pasirinkite teisingą atidėjimo terminą ir pradžią");
                return;
            }
            else {
                deferral = new Deferral(
                        deferralStartDate.getValue(),
                        Integer.parseInt(deferralTerm.getText()),
                        Double.parseDouble(deferralPercentage.getText())
                );
            }
        }


        Loan loan = new Loan(amount, (int) termMonths, interestRate, start, deferral);

        LoanCalculator calculator;

        if (loanChoice.getValue().equals("Anuiteto")) {
            calculator = new AnnuityCalculator(loan);
        } else {
            calculator = new LinearCalculator(loan);
        }

        payments = calculator.calculatePayments();
        filterChecked = false;

        // table
        paymentTable.getItems().clear();
        paymentTable.getItems().addAll(payments);

        // graph
        paymentChart.getData().clear();

        paymentChart.getData().add(ChartDataBuilder.buildAnnuitySeries(loan));
        paymentChart.getData().add(ChartDataBuilder.buildLinnearSeries(loan));

    }

    @FXML
    void filter(ActionEvent event) {
        if (!filterChecked) return;
        if(startFilterDate.getValue() == null || endFilterDate.getValue() == null) {
            resultLabel.setText("Pasirinkite teisingą filtravimo datą");
            return;
        }

        LocalDate start = startFilterDate.getValue();
        LocalDate end = endFilterDate.getValue();

        if(start.isAfter(end)) {
            resultLabel.setText("Termino pabaiga negali būti anksčiau už pradžią");
            return;
        }

        filteredList = PaymentFilter.filteredList(payments, start, end);
        paymentTable.getItems().clear();
        paymentTable.getItems().addAll(filteredList);
    }

    @FXML
    void printReport(ActionEvent event) {
        if(payments == null || payments.isEmpty()) {
            resultLabel.setText("Pirmiausia apskaičiuokite mokėjimus");
            return;
        }

        try {
            if(!filterChecked) ReportGenerator.generateCSV(payments, "ataskaita");
            else ReportGenerator.generateCSV(filteredList, "ataskaita");

            resultLabel.setText("Ataskaita išsaugota!");
        } catch (IOException e) {
            resultLabel.setText("Klaida išsaugant ataskaitą");
        }
    }



    @FXML
    void toggleDeferral(ActionEvent event) {
        boolean isChecked = deferralExists.isSelected();
        deferralStartDate.setDisable(!isChecked);
        deferralTerm.setDisable(!isChecked);
        deferralPercentage.setDisable(!isChecked);

        if (!isChecked) {
            deferralStartDate.setValue(null);
            deferralTerm.clear();
            deferralPercentage.clear();
        }
    }

    @FXML
    void toggleFilter(ActionEvent event) {
        filterChecked = filterExists.isSelected();
        startFilterDate.setDisable(!filterChecked);
        endFilterDate.setDisable(!filterChecked);

        if(!filterChecked) {
            startFilterDate.setValue(null);
            endFilterDate.setValue(null);

            if (payments != null && !payments.isEmpty()) {
                paymentTable.getItems().clear();
                paymentTable.getItems().addAll(payments);
            }
        }
    }

    @FXML
    public void initialize() {

        loanChoice.getItems().addAll("Anuiteto", "Linijinis");
        loanChoice.setValue("Anuiteto");

        nrCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNumber()).asObject());
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDate()));
        paymentCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPaymentStr()));
        interestCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getInterestStr()));
        principalCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrincipalStr()));
        balanceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getBalanceStr()));
        paidCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPaidStr()));

        deferralStartDate.setDisable(true);
        deferralTerm.setDisable(true);
        deferralPercentage.setDisable(true);

        startFilterDate.setDisable(true);
        endFilterDate.setDisable(true);
    }
}
