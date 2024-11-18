package com.am.exchangeconverter.service;

import com.am.exchangeconverter.model.ExchangeRate;

public class ConversionCalculator {
    public double convert(ExchangeRate exchangeRate, String targetCurrency, double amount) {
        try {
            Double rate = exchangeRate.getRates().get(targetCurrency);
            if (rate == null) {
                throw new IllegalArgumentException("Target currency not found: " + targetCurrency);
            }
            return amount * rate;
        } catch (IllegalArgumentException e) {
            System.err.println("Conversion error: " + e.getMessage());
            return 0;
        }
    }
}
