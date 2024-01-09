package com.miyawaki.batchsystem.csvexporter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private String url;
    private String user;
    private String password;

    public DatabaseConfig() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }

            prop.load(input);

            this.url = prop.getProperty("oracledb.hr.url");
            this.user = prop.getProperty("oracledb.hr.user");
            this.password = prop.getProperty("oracledb.hr.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}