package com.miyawaki.batchsystem.csvimporter.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.miyawaki.batchsystem.csvimporter.dto.JobsDto;

public class CsvDatabaseImporterTest {
    @Test
    public void testReadMethods() throws IOException {
        CsvDatabaseImporter importer = new CsvDatabaseImporter();

        String filePath = getClass().getResource("/test_data.csv").getPath();

        Instant start1 = Instant.now();
        List<JobsDto> result1 = importer.readCsvFile(filePath);
        Instant end1 = Instant.now();

        Instant start2 = Instant.now();
        List<JobsDto> result2 = importer.readRambdaCsvFile(filePath);
        Instant end2 = Instant.now();

        assertEquals(result1, result2, "The results of readCsvFile and readRambdaCsvFile should be the same");

        long time1 = Duration.between(start1, end1).toMillis();
        long time2 = Duration.between(start2, end2).toMillis();

        System.out.println("Execution time of readCsvFile: " + time1 + " ms");
        System.out.println("Execution time of readRambdaCsvFile: " + time2 + " ms");
    }
}