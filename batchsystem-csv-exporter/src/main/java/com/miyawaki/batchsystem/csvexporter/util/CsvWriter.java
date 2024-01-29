package com.miyawaki.batchsystem.csvexporter.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvWriter {

    public void writeToCsv(List<String[]> data) throws IOException {
        // String filePath = "../csv/export/";
        String filePath = "../csv/export/";

        // 現在の日時を取得してフォーマットする
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);

        // ファイル名に日時を追加
        String fileName = "data_" + formattedDateTime + ".csv";
        String fullFilePath = filePath + fileName;

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(fullFilePath), CSVFormat.DEFAULT)) {
            for (String[] record : data) {
                printer.printRecord((Object[]) record);
            }
        }
    }
}