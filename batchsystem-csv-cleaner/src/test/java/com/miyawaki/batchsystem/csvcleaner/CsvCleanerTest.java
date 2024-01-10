package com.miyawaki.batchsystem.csvcleaner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.miyawaki.batchsystem.csvcleaner.service.CsvCleaner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CsvCleanerTest {
    @TempDir
    Path tempDir;

    @Test
    public void testExecute() throws IOException {
        CsvCleaner cleaner = new CsvCleaner(tempDir.toString());

        // テスト用のCSVファイルを作成
        Path csv1 = Files.createFile(tempDir.resolve("test1.csv"));
        Path csv2 = Files.createFile(tempDir.resolve("test2.csv"));

        // CsvCleanerを実行
        cleaner.execute(1);

        // 最も古いファイルが削除されていることを確認
        assertFalse(Files.exists(csv1));
        assertTrue(Files.exists(csv2));
    }
}
