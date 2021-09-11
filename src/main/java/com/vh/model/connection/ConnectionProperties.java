package com.vh.model.connection;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ConnectionProperties {
    private String url;
    private String user;
    private String password;

    public ConnectionProperties() {
        try (
                InputStream input = new FileInputStream("src/main/resources/connection.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
