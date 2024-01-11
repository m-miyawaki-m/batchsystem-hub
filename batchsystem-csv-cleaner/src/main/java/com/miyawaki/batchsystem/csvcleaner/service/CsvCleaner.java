package com.miyawaki.batchsystem.csvcleaner.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Comparator;

public class CsvCleaner {
    private String directoryPath;

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
        String[] directories = {"../csv/import/", "../csv/export/", "../csv/generate/"};

        for (String directory : directories) {
            try {
                Files.walkFileTree(Paths.get(directory), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.toString().endsWith(".csv")) {
                            Files.delete(file);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}