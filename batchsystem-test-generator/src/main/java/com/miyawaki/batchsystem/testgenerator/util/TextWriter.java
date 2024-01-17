package com.miyawaki.batchsystem.testgenerator.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class TextWriter {
    public void writeToText(String data) throws IOException {
        String filePath = getFilePath();

        // 現在の日時を取得してフォーマットする
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);

        // ファイル名に日時を追加
        String fileName = "/data_" + formattedDateTime + ".sql";
        String fullFilePath = filePath + fileName;

        // try (CSVPrinter printer = new CSVPrinter(new FileWriter(fullFilePath), CSVFormat.DEFAULT)) {
        //     // for (String[] record : data) {
        //     //     printer.printRecord((Object[]) record);
        //     // }
        //     printer.printRecord(data);
        // }
        try (FileWriter writer = new FileWriter(fullFilePath)) {
            // for (String sql : data) {
            //     writer.write(sql);
            //     writer.write(System.lineSeparator());
            // }
            writer.write(data);
            writer.write(System.lineSeparator()); // 改行コードを書き込む
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
            String textFile = prop.getProperty("oracledb.hr.textfile", "");
            return basePath + textFile;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}