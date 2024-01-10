package com.miyawaki.batchsystem.csvcleaner.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

public class CsvCleaner {
    private String directoryPath;

    public CsvCleaner(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void execute(int csvCount) {
        File directory = new File(directoryPath);

        // ディレクトリ内のCSVファイルを取得
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (files != null && files.length > csvCount) {
            // ファイルを最終変更日時でソート
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));

            // 最も古いファイルを削除
            try {
                Files.deleteIfExists(files[0].toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}