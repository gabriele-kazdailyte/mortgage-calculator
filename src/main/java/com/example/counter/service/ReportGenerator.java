package com.example.counter.service;

import com.example.counter.model.Payment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public static void generateCSV(List<Payment> payments, String filePath) throws IOException {
        if (payments == null)
            return;

        int n = 1;
        String name;
        do {
            name = filePath + n + ".csv";
            n++;
        } while (new java.io.File(name).exists());

        try (FileWriter writer = new FileWriter(name, java.nio.charset.StandardCharsets.UTF_8)) {
            writer.write("\uFEFF");
            writer.write("Nr.,Data,Mokėjimas,Palūkanos,Pagrindinis,Likutis\n");

            for (Payment p : payments) {
                writer.write(p.getNumber() + "," +
                        p.getDate() + "," +
                        p.getPaymentStr() + "," +
                        p.getInterestStr() + "," +
                        p.getPrincipalStr() + "," +
                        p.getBalanceStr() + "\n");
            }
        }
    }
}
