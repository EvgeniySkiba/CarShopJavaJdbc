/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.se.carsstore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.ResultSet;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Skiba
 */
public class Lesson {

    /// https://www.codeproject.com/Tips/815186/Java-JDBC-SQLite-Read-Data-from-User-selected-db-T
    ///Java & JDBC & SQLite: Read Data from User-selected db Table and Show in JTable
    public static void main(String[] args) {

        //4. CTRL+/ Toggle Comment
        //5. ALT+SHIFT+F Format Code
        //8. ALT+INSERT Generate Code
        // ALT+F7 Find Usages
        Connection connection = null; // храним соеденение с базой данных
        Statement statement = null;// выполнение запросов
        ResultSet resultSet = null;

        System.out.println("HERE");
        try {
            // регистрируем драйвер
            Driver d = (Driver) Class.forName("org.sqlite.JDBC").newInstance();

            //создание и подключение к базе данных
            String url = "jdbc:sqlite:G:\\PROJECT\\Leaarning\\Java\\CarsSaling\\dataBase\\CarShop.db3";
            connection = DriverManager.getConnection(url);

            String sql = "select * from CarModel";
            statement = connection.createStatement();

            // выполнение запроса 
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name_ru"));
            }

            // get all tables -->
            System.out.println("************ Current table ***********");
            ResultSet rs = null;
            DatabaseMetaData meta = connection.getMetaData();
            rs = meta.getTables(null, null, null, new String[]{"TABLE"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                System.out.println(tableName);
            }

            // get all tables <--
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            //какой класс и метод делают вызов.
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String message = "";
            if (stackTraceElements.length >= 3) {
                StackTraceElement element = stackTraceElements[1];
                String className = element.getClassName();
                String methodName = element.getMethodName();
                message = className + ": " + methodName;
            }
            System.out.println("message : " +message);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
