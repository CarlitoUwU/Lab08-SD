package com.sd.lab8sd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/Empresa_SD"
            + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "sd_user";
    private static final String PASS = "123456789@";
    private static final Logger LOGGER = Logger.getLogger(ConexionDB.class.getName());

    private ConexionDB() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error conectando a BD", e);
            return null;
        }
    }
}

