package com.miyawaki.batchsystem.csvimporter;

import java.io.IOException;

import com.miyawaki.batchsystem.csvimporter.service.CsvDatabaseImporter;

public class App {
    public static void main(String[] args) throws IOException {
        CsvDatabaseImporter csvDbImporter = new CsvDatabaseImporter();
        csvDbImporter.execute();
    }
}
