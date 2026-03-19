package com.example.counter.service;

import com.example.counter.model.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentFilter {

    public static List<Payment> filteredList (List<Payment> payments, LocalDate start, LocalDate end){
        List<Payment> filteredList = new ArrayList<>();

        for(Payment line : payments) {
            if(!line.getDate().isBefore(start) && !line.getDate().isAfter(end)) {
                filteredList.add(line);
            }
        }
        return filteredList;
    }
}
