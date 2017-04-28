/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.se.carsstore;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skiba
 */
public class SqlLiteConnection {

    public static Connection connection;

    public static Connection getConnection() {

        try {
            //динамическая регистрация драйвера 
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();

            //TODO: need to create constructor with usr as a param
            String url = "jdbc:sqlite:G:\\PROJECT\\Leaarning\\Java\\CarsSaling\\dataBase\\CarShop.db3";

            if (connection == null) {
                connection = DriverManager.getConnection(url);
            }

            return connection;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SqlLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
