package com.miyawaki.batchsystem.csvexporter.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miyawaki.batchsystem.csvexporter.repository.DatabaseConfig;
import com.miyawaki.batchsystem.csvexporter.repository.DatabaseConnectionManager;
import com.miyawaki.batchsystem.csvexporter.util.CsvWriter;

public class DatabaseCsvExporter {
    private DatabaseConnectionManager dbConnectionManager;

    public DatabaseCsvExporter() {
        this.dbConnectionManager = new DatabaseConnectionManager(new DatabaseConfig());
    }

    private List<String[]> fetchViewData() throws SQLException {
        List<String[]> data = new ArrayList<>();
        // String sql = "SELECT * FROM hr.employees_jobs_v";
        String sql = "SELECT * FROM hr.jobs";

        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            // ヘッダー行を取得
            ResultSetMetaData metaData = resultSet.getMetaData();
            String[] header = new String[metaData.getColumnCount()];
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                header[i - 1] = metaData.getColumnName(i);
            }
            data.add(header);

            // データ行を取得
            while (resultSet.next()) {
                String[] row = new String[4];
                for (int i = 1; i <= 4; i++) {
                    row[i - 1] = resultSet.getString(i);
                }
                data.add(row);
            }
        }

        return data;
    }

    public void execute() {
        try {
            List<String[]> viewData = fetchViewData();

            CsvWriter writer = new CsvWriter();
            writer.writeToCsv(viewData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
