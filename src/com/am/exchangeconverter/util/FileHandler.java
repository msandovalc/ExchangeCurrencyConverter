package com.am.exchangeconverter.util;

public interface FileHandler<T> {
    void writeToFile(String fileName, T data);
    T readFromFile(String fileName);
}
