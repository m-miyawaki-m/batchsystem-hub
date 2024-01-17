package com.miyawaki.batchsystem.testgenerator.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.miyawaki.batchsystem.testgenerator.repository.DatabaseConfig;
import com.miyawaki.batchsystem.testgenerator.repository.DatabaseConnectionManager;
import com.miyawaki.batchsystem.testgenerator.util.CsvWriter;
import com.miyawaki.batchsystem.testgenerator.util.TextWriter;

public class DatabaseCsvExporter {
    private DatabaseConnectionManager dbConnectionManager;

    public DatabaseCsvExporter() {
        this.dbConnectionManager = new DatabaseConnectionManager(new DatabaseConfig());
    }

    public void execute() {
        try {
            List<String[]> tableTypeData = fetchLimitTableData();
            String insertStatement = createInsertStatement(tableTypeData, getTableName());
            TextWriter textWriter = new TextWriter();
            textWriter.writeToText(insertStatement);

            CsvWriter csvWriter = new CsvWriter();
            csvWriter.writeToCsv(tableTypeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createInsertStatement(List<String[]> tableData, String tableName) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
    
        for (String[] row : tableData) {
            if (columns.length() > 0) {
                columns.append(", ");
                values.append(", ");
            }
            columns.append(row[0]); // column name
            String dataType = row[1];
            int dataLength = Integer.parseInt(row[2]);
            int dataPrecision = Integer.parseInt(row[3]);
            int dataScale = Integer.parseInt(row[4]);
            

            if ("NUMBER".equals(dataType)) { //NUMBER型の場合
                if (dataScale > 0) {
                    String format = "%." + dataScale + "f";
                    double maxValue = Math.pow(10, dataPrecision - dataScale) - Math.pow(10, -dataScale);
                    values.append(String.format(format, maxValue));
                } else {
                    values.append(String.valueOf((int) Math.pow(10, dataPrecision) - 1)); // max number
                }
            }else if ("VARCHAR2".equals(dataType) || "CHAR".equals(dataType)) { //VARCHAR2型の場合 or CHAR型の場合
                char[] chars = new char[dataLength];
                Arrays.fill(chars, 'A');
                values.append("'").append(new String(chars)).append("'"); // max length string
            } else {
                values.append("'").append(row[2]).append("'"); // data length
            }
        }
    
        return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, columns, values);
    }

    /*
     * テーブルのカラム名、データ型、データ長を取得する
     * @return List<String[]> テーブルのカラム名、データ型、データ長のリスト
     */

    private List<String[]> fetchLimitTableData() throws SQLException {
        String tableName = getTableName();
        // String filePath = getFilePath();
        List<String[]> data = new ArrayList<>();
        // String query = String.format("SELECT COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_PRECISION, FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = '%s'", tableName);

        String query = String.format("SELECT COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_PRECISION,DATA_SCALE FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '%s'", tableName);
        
        try (Connection connection = dbConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();){

                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME");
                    String dataType = rs.getString("DATA_TYPE");
                    int dataLength = rs.getInt("DATA_LENGTH");
                    int dataPrecision = rs.getInt("DATA_PRECISION");
                    int dataScale = rs.getInt("DATA_SCALE");
    
                    String[] row = new String[]{columnName, dataType, String.valueOf(dataLength), String.valueOf(dataPrecision), String.valueOf(dataScale)};
                    data.add(row);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getTableName() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return "";
            }

            prop.load(input);
            return prop.getProperty("oracledb.hr.table");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
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
            String csvFile = prop.getProperty("oracledb.hr.csvfile", "");
            return basePath + csvFile;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
