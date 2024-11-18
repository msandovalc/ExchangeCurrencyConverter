package com.am.exchangeconverter.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileHandler<T> implements FileHandler<T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Class<T> type;

    public JsonFileHandler(Class<T> type) {
        this.type = type;
    }

    @Override
    public void writeToFile(String fileName, T data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public T readFromFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return null;
        }
    }
}
