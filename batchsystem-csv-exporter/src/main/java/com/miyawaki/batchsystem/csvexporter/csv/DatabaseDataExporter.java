package com.miyawaki.batchsystem.csvexporter.csv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miyawaki.batchsystem.csvexporter.util.DatabaseConnectionManager;

public class DatabaseDataExporter {
    private DatabaseConnectionManager dbConnectionManager;

    public DatabaseDataExporter(DatabaseConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    public List<String[]> fetchViewData() throws SQLException {
        List<String[]> data = new ArrayList<>();
        String sql = "SELECT * FROM hr.employees_jobs_v";

        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // ビューの各列を取得してリストに追加
                String[] row = new String[13]; // 13は選択された列の数
                for (int i = 1; i <= 13; i++) {
                    row[i - 1] = resultSet.getString(i);
                }
                data.add(row);
            }
        }

        return data;
    }
}
