package com.miyawaki.batchsystem.csvcleaner;

import com.miyawaki.batchsystem.csvcleaner.service.CsvCleaner;

public class App {
    public static void main(String[] args) {
        CsvCleaner csvCleaner = new CsvCleaner();
        // csvCleaner.execute();
        csvCleaner.deleteCsvFiles();
    }
}
