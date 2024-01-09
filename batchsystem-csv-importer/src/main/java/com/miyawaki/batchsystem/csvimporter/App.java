package com.miyawaki.batchsystem.csvimporter;

import java.io.IOException;

import com.miyawaki.batchsystem.csvimporter.csv.CsvDbImporter;

public class App {
    public static void main(String[] args) throws IOException {
        CsvDbImporter csvDbImporter = new CsvDbImporter();
        csvDbImporter.execute();
    }
}
