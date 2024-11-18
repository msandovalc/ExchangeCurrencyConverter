package com.am.exchangeconverter.service;

import com.am.exchangeconverter.api.CurrencyDataProvider;
import com.am.exchangeconverter.model.ExchangeRate;

public class ConversionService {
    private final CurrencyDataProvider dataProvider;

    public ConversionService(CurrencyDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public double convert(String baseCurrency, String targetCurrency, double amount) {
        ExchangeRate exchangeRate = dataProvider.getExchangeRates(baseCurrency);
        if (exchangeRate != null) {
            ConversionCalculator calculator = new ConversionCalculator();
            return calculator.convert(exchangeRate, targetCurrency, amount);
        } else {
            System.err.println("Failed to fetch exchange rates for: " + baseCurrency);
            return 0;
        }
    }
}
