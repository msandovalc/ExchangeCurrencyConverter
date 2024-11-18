package com.am.exchangeconverter.main;

import com.am.exchangeconverter.api.ExchangeRateApiProvider;
import com.am.exchangeconverter.model.ExchangeRate;

import java.util.List;
import java.util.Scanner;

public class CurrencyConverterApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ExchangeRateApiProvider apiProvider = new ExchangeRateApiProvider();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            // Mostrar el menú
            System.out.println("\nCurrency Converter Menu:");
            System.out.println("1. Start Currency Converter");
            System.out.println("2. Exit");
            System.out.print("Please choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer after reading the integer

            switch (option) {
                case 1:
                    startCurrencyConverter();
                    break;
                case 2:
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Método para iniciar la conversión de monedas
    private static void startCurrencyConverter() {
        System.out.println("Welcome to the Currency Converter!");

        List<String> supportedCurrencies = apiProvider.getSupportedCurrencies();

        // Mostrar las monedas soportadas en líneas de 10 monedas
        if (!supportedCurrencies.isEmpty()) {
            System.out.println("Supported currencies: ");
            int count = 0;
            for (String currency : supportedCurrencies) {
                System.out.print(currency + " ");
                count++;
                if (count % 10 == 0) {
                    System.out.println(); // Nueva línea cada 10 monedas
                }
            }
            System.out.println(); // Línea final para separar el listado
        }

        // Validar la moneda base con validación
        String baseCurrency = getCurrencyFromUser(supportedCurrencies, "Enter base currency (e.g., USD): ");

        // Validar la moneda objetivo con validación de 3 intentos
        String targetCurrency = getCurrencyFromUser(supportedCurrencies, "Enter target currency (e.g., EUR): ");

        // Solicitar la cantidad
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        // Obtener las tasas de cambio
        ExchangeRate exchangeRate = apiProvider.getExchangeRates(baseCurrency);
        if (exchangeRate != null && exchangeRate.getRates().containsKey(targetCurrency)) {
            double rate = exchangeRate.getRates().get(targetCurrency);
            double result = amount * rate;
            System.out.println(amount + " " + baseCurrency + " = " + result + " " + targetCurrency);
        } else {
            System.out.println("Error: Unable to fetch exchange rates for " + baseCurrency + " to " + targetCurrency);
        }
    }

    // Método modular para obtener y validar monedas del usuario
    private static String getCurrencyFromUser(List<String> supportedCurrencies, String prompt) {
        System.out.print(prompt);
        String currency = scanner.nextLine().toUpperCase();

        int attempts = 0;
        // Validar que la moneda esté en las monedas soportadas
        while (!supportedCurrencies.contains(currency) && attempts < 3) {
            attempts++;
            if (attempts < 3) {
                System.out.println("Invalid currency. Please choose from the supported currencies. (Attempts: " + attempts + ")");
                System.out.print(prompt);
                currency = scanner.nextLine().toUpperCase();
            } else {
                System.out.println("Invalid currency entered 3 times. Restarting conversion... (Attempts: " + attempts + ")");
                return getCurrencyFromUser(supportedCurrencies, prompt); // Reiniciar el flujo de conversión
            }
        }
        return currency;
    }
}
