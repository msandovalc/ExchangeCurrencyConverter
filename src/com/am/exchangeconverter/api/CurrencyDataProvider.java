package com.am.exchangeconverter.api;

import com.am.exchangeconverter.model.ExchangeRate;

import java.util.List;

public interface CurrencyDataProvider {
    ExchangeRate getExchangeRates(String baseCurrency);
    List<String> getSupportedCurrencies();
}