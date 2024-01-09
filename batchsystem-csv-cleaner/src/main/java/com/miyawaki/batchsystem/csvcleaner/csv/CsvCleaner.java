package com.miyawaki.batchsystem.csvcleaner.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

public class CsvCleaner {
    public void execute(int csvCount) {
        String directoryPath = "./csv/";
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
