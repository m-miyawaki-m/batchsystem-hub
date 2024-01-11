package com.miyawaki.batchsystem.csvgenerator.service;

import org.apache.commons.csv.*;

import com.miyawaki.batchsystem.csvgenerator.util.JobTitle;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomDataGenerator {

    private final int MIN_SALARY = 3000;
    private final int MAX_SALARY = 40000;
    private final Random random = new Random();

    public void execute() {
        int recordCount = 5;
        generateCsvFile(recordCount);
    }

    private void generateCsvFile(int recordCount) {
        String filePath = "../csv/generate/";

        // 現在の日時を取得してフォーマットする
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);

        // ファイル名に日時を追加
        String fileName = "data_" + formattedDateTime + ".csv";
        String fullFilePath = filePath + fileName;

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(fullFilePath),
                CSVFormat.DEFAULT.withHeader("JOB_ID", "JOB_TITLE", "MIN_SALARY", "MAX_SALARY"))) {
            for (int i = 0; i < recordCount; i++) {
                JobTitle jobTitle = getRandomJobTitle();
                int minSalary = random.nextInt((MAX_SALARY - MIN_SALARY) + 1) + MIN_SALARY;
                int maxSalary = minSalary + random.nextInt((MAX_SALARY - minSalary) + 1);
                printer.printRecord(jobTitle.getJobId(), jobTitle.getTitle(), minSalary, maxSalary);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JobTitle getRandomJobTitle() {
        JobTitle[] titles = JobTitle.values();
        return titles[random.nextInt(titles.length)];
    }
}
