package com.miyawaki.batchsystem.csvexporter;

import java.util.List;

import com.miyawaki.batchsystem.csvexporter.csv.DatabaseDataExporter;
import com.miyawaki.batchsystem.csvexporter.util.CsvWriter;
import com.miyawaki.batchsystem.csvexporter.util.DatabaseConfig;
import com.miyawaki.batchsystem.csvexporter.util.DatabaseConnectionManager;

public class App {
    public static void main(String[] args) {
        try {
            DatabaseConnectionManager dbConnectionManager = new DatabaseConnectionManager(new DatabaseConfig());
            DatabaseDataExporter fetcher = new DatabaseDataExporter(dbConnectionManager);
            List<String[]> viewData = fetcher.fetchViewData();

            CsvWriter writer = new CsvWriter();
            writer.writeToCsv(viewData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
