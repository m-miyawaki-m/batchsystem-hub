package com.miyawaki.batchsystem.csvimporter.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.miyawaki.batchsystem.csvimporter.dto.JobsDto;
import com.miyawaki.batchsystem.csvimporter.util.DatabaseConfig;
import com.miyawaki.batchsystem.csvimporter.util.DatabaseConnectionManager;

public class CsvDbImporter {

    private DatabaseConfig config;
    private DatabaseConnectionManager dbConnectionManager;

    public CsvDbImporter() {
        config = new DatabaseConfig();
        dbConnectionManager = new DatabaseConnectionManager(config);
    }

    public List<JobsDto> readCsvFile(String filePath) throws IOException {
        List<JobsDto> jobList = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                JobsDto job = new JobsDto(
                        record.get("JOB_ID"),
                        record.get("JOB_TITLE"),
                        Integer.parseInt(record.get("MIN_SALARY")),
                        Integer.parseInt(record.get("MAX_SALARY")));
                jobList.add(job);
            }
        }
        return jobList;
    }

    public void importToDatabase(List<JobsDto> jobList) {
        // まずテーブルをトランケート
        // truncateTable("jobs_bk");

        String insertSql = "INSERT INTO jobs_bk (job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(insertSql)) {
            // データをインポート
            for (JobsDto job : jobList) {
                statement.setString(1, job.getJobId());
                statement.setString(2, job.getJobTitle());
                statement.setInt(3, job.getMinSalary());
                statement.setInt(4, job.getMaxSalary());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void truncateTable(String tableName) {
        String sql = "TRUNCATE TABLE " + tableName;
        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        String directoryPath = "./csv/import";
        File directory = new File(directoryPath);

        // ディレクトリ内のCSVファイルを取得
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (files != null) {
            // ファイルを最終変更日時でソート
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));

            // 最も古いファイルのパスを取得
            File oldestFile = files[0];
            String oldestFilePath = oldestFile.getAbsolutePath();

            // 最も古いファイルを読み込む
            try {
                List<JobsDto> jobsList = readCsvFile(oldestFilePath);
                importToDatabase(jobsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}