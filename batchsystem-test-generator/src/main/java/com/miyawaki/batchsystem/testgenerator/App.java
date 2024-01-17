package com.miyawaki.batchsystem.testgenerator;

import com.miyawaki.batchsystem.testgenerator.service.DatabaseCsvExporter;

public class App
{
    public static void main( String[] args )
    {
        DatabaseCsvExporter exporter = new DatabaseCsvExporter();
        exporter.execute();
    }
}
