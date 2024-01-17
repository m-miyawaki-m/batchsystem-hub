package com.miyawaki.batchsystem.testgenerator.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvWriter {
    public void writeToCsv(List<String[]> data) throws IOException {
        String filePath = getFilePath();

        // 現在の日時を取得してフォーマットする
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);

        // ファイル名に日時を追加
        String fileName = "/data_" + formattedDateTime + ".csv";
        String fullFilePath = filePath + fileName;

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(fullFilePath), CSVFormat.DEFAULT)) {
            for (String[] record : data) {
                printer.printRecord((Object[]) record);
            }
        }
    }

    public String getFilePath() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return "";
            }

            prop.load(input);
            String basePath = prop.getProperty("base.path", "");
            String csvFile = prop.getProperty("oracledb.hr.csvfile", "");
            return basePath + csvFile;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}