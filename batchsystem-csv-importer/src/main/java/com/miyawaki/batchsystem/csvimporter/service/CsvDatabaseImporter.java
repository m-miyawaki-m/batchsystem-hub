package com.miyawaki.batchsystem.csvimporter.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.miyawaki.batchsystem.csvimporter.annotation.InvestigationRefactoring;
import com.miyawaki.batchsystem.csvimporter.dto.JobsDto;
import com.miyawaki.batchsystem.csvimporter.repository.DatabaseConfig;
import com.miyawaki.batchsystem.csvimporter.repository.DatabaseConnectionManager;

public class CsvDatabaseImporter {

    private DatabaseConfig config;
    private DatabaseConnectionManager dbConnectionManager;

    public CsvDatabaseImporter() {
        config = new DatabaseConfig();
        dbConnectionManager = new DatabaseConnectionManager(config);
    }

    List<JobsDto> readCsvFile(String filePath) throws IOException {
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

    @InvestigationRefactoring
    List<JobsDto> readRambdaCsvFile(String filePath) throws IOException {
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            return StreamSupport.stream(parser.spliterator(), false)
                    .map(record -> new JobsDto(
                            record.get("JOB_ID"),
                            record.get("JOB_TITLE"),
                            Integer.parseInt(record.get("MIN_SALARY")),
                            Integer.parseInt(record.get("MAX_SALARY"))))
                    .collect(Collectors.toList());
        }
    }

    private void importToDatabase(List<JobsDto> jobList) {
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

    @InvestigationRefactoring
    private void importToDatabaseRambda(List<JobsDto> jobList) {
        // まずテーブルをトランケート
        // truncateTable("jobs_bk");

        String insertSql = "INSERT INTO jobs_bk (job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(insertSql)) {
            // データをインポート
            jobList.forEach(job -> {
                try {
                    statement.setString(1, job.getJobId());
                    statement.setString(2, job.getJobTitle());
                    statement.setInt(3, job.getMinSalary());
                    statement.setInt(4, job.getMaxSalary());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void truncateTable(String tableName) {
        String sql = "TRUNCATE TABLE " + tableName;
        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        String directoryPath = "../csv/import";
        File directory = new File(directoryPath);

        // ディレクトリ内のCSVファイルを取得
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (files != null) {
            // ファイルを最終変更日時でソート
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));

            String filePath = "";

            // 最も古いファイルのパスを取得
            // filePath = getOldestFilePath(files);

            // 最も新しいファイルのパスを取得
            filePath = getNewestFilePath(files);

            try {
                List<JobsDto> jobsList = readCsvFile(filePath);
                importToDatabase(jobsList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String getOldestFilePath(File[] files) {
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        return files[0].getAbsolutePath();
    }

    private String getNewestFilePath(File[] files) {
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        return files[files.length - 1].getAbsolutePath();
    }
}