package com.am.exchangeconverter.api;

import com.am.exchangeconverter.model.ExchangeRate;
import com.google.gson.Gson;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ExchangeRateApiProvider implements CurrencyDataProvider {

    private static String API_URL;
    private static String API_KEY;

    static {
        try (InputStream input = ExchangeRateApiProvider.class.getClassLoader().getResourceAsStream("com/am/exchangeconverter/resources/config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            // For debug
            //System.out.println(input);
            Properties prop = new Properties();
            prop.load(input);
            API_URL = prop.getProperty("api.url");
            API_KEY = prop.getProperty("api.key");
            // For debug
            //System.out.println("API_URL: " + API_URL);
            //System.out.println("API_KEY: " + API_KEY);

        } catch (IOException ex) {
            System.err.println("Error loading config.properties: " + ex.getMessage());
        }
    }

    @Override
    public ExchangeRate getExchangeRates(String baseCurrency) {
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + baseCurrency + "?apiKey=" + API_KEY))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // For debug
            //String json = response.body();
            //System.out.println(json);

            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), ExchangeRate.class);
            } else {
                throw new RuntimeException("API error: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            System.err.println("Error fetching exchange rates: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> getSupportedCurrencies() {
        try {
            // Realizar la solicitud para obtener las monedas soportadas
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "USD")) // Se usa USD como base
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            if (response.statusCode() == 200) {
                ExchangeRate exchangeRate = new Gson().fromJson(response.body(), ExchangeRate.class);
                return List.copyOf(exchangeRate.getRates().keySet());
            } else {
                System.err.println("Error fetching supported currencies: " + response.statusCode() + " - " + response.body());
                return List.of(); // Devuelve una lista vacía en caso de error
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching supported currencies: " + e.getMessage());
            return List.of(); // Devuelve una lista vacía en caso de error
        }
    }

}
