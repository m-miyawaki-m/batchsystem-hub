package com.miyawaki.batchsystem.csvcleaner.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CsvCleaner {
    private String directoryPath;

    private static final Logger logger = LogManager.getLogger(CsvCleaner.class);

    public CsvCleaner(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public CsvCleaner() {
        this("../csv/import/");
    }

    public void execute() {
        File directory = new File(directoryPath);

        // ディレクトリ内のCSVファイルを取得
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (files != null && files.length > 0) {
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

    public void deleteCsvFiles() {
        // String[] directories = {"../csv/import/", "../csv/export/",
        // "../csv/generate/"};

        // 環境変数 DIRECTORIES を読み込む
        // デバッグ時の設定例
        // BASE_PATH=/home/vscode/github/batchsystem-hub/
        // directories=("${BASE_PATH}csv/import/" "${BASE_PATH}csv/export/"
        // "${BASE_PATH}csv/generate/")
        // export CSV_DIRECTORIES="${directories[@]}"
        String[] directories = System.getenv("CSV_DIRECTORIES").split(" ");

        for (String directory : directories) {
            File dirFile = new File(directory);
            File[] files = dirFile.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            int deleteCount = 0;
            if (files != null) {
                for (File file : files) {
                    try {
                        Files.deleteIfExists(file.toPath());
                        deleteCount++;
                    } catch (IOException e) {
                        logger.error("Error deleting file: " + file.getAbsolutePath(), e);
                    }
                }
            }
            logger.info("Deleted " + deleteCount + " files from directory: " + directory);
        }
    }
}