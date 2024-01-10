package com.miyawaki.batchsystem.csvexporter;

import com.miyawaki.batchsystem.csvexporter.service.DatabaseCsvExporter;

public class App {
    public static void main(String[] args) {

        DatabaseCsvExporter exporter = new DatabaseCsvExporter();
        exporter.execute();
    }
}
