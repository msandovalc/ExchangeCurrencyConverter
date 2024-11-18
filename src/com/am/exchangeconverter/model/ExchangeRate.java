package com.am.exchangeconverter.model;

import java.util.Map;

public class ExchangeRate {
    private String base;
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
