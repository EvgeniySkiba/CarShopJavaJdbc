/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.se.tablemodels;

import javax.swing.table.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Skiba
 */
public class TableModel extends AbstractTableModel {

    // данные
    private Object[][] contents;

    //имена столбцов
    private String[] columnNames;

    //типы столбцов
    private Class[] columnClasses;

    public TableModel(Connection connection, String tableName) throws SQLException {
        super();
        getTableContents(connection, tableName);
    }

    private void getTableContents(Connection connection, String tableName) throws SQLException {

        //получить метаданные по столбцам 
        DatabaseMetaData meta = connection.getMetaData();

        //получить метаданные по столбцам
        ResultSet rs = meta.getColumns(null, null, tableName, null);

        //список имен столбов
        ArrayList colNameList = new ArrayList();

        //список типов столбцов
        ArrayList colTypeList = new ArrayList();

        // список по всем столбцам таблицы 
        // для каждого столбца определить имя и тип 
        while (rs.next()) {
            // добавить в список имя столбца 
            colNameList.add(rs.getString("COLUMN_NAME"));

            //определить тип столбца 
            int dbType = rs.getInt("DATA_TYPE");

            //Выбрать нужный тип 
            switch (dbType) {
                case Types.INTEGER:
                    colTypeList.add(Integer.class);
                    break;

                case Types.FLOAT:
                    colTypeList.add(Float.class);
                    break;

                case Types.DOUBLE:
                case Types.REAL:
                    colTypeList.add(Double.class);
                    break;

                case Types.DATE:
                case Types.TIME:
                case Types.TIMESTAMP:
                    colTypeList.add(java.sql.Date.class);
                    break;

                default:
                    colTypeList.add(String.class);
                    break;
            }
        }

        //имена  столбцов сохранить в отдельный массив column name 
        columnNames = new String[colNameList.size()];
        colNameList.toArray(columnNames);

        // типы столбцов сохранить в отдельный массив
        columnClasses = new Class[colTypeList.size()];
        colTypeList.toArray(columnClasses);

        Statement statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName);

        //хранит записи из таблицы
        ArrayList rowList = new ArrayList();

        //цикл по всем записям таблицы 
        while (rs.next()) {
            //храним данные по каждому столбцу
            ArrayList cellList = new ArrayList();

            for (int i = 0; i < columnClasses.length; i++) {
                Object cellValue = null;

                if (columnClasses[i] == String.class) {
                    cellValue = rs.getString(columnNames[i]);
                } else if (columnClasses[i] == Integer.class) {
                    cellValue = new Integer(rs.getInt(columnNames[i]));
                } else if (columnClasses[i] == Float.class) {
                    cellValue = new Float(rs.getInt(columnNames[i]));
                } else if (columnClasses[i] == Double.class) {
                    cellValue = new Double(rs.getDouble(columnNames[i]));
                } else if (columnClasses[i] == java.sql.Date.class) {
                    cellValue = rs.getDate(columnNames[i]);
                } else {
                    System.out.println("Can not determine the type of field : " + columnNames[i]);
                }

                cellList.add(cellValue);
            }

            Object[] cells = cellList.toArray();
            rowList.add(cells);
        }

        contents = new Object[rowList.size()][];

        for (int i = 0; i < contents.length; i++) {
            contents[i] = (Object[]) rowList.get(i);
        }

        rs.close();
        statement.close();
    }

    @Override
    public int getRowCount() {
        return contents.length;
    }

    @Override
    public int getColumnCount() {
        if (contents.length == 0) {
            return 0;
        } else {
            return contents[0].length;
        }
    }

    /**
     * для каждого столбца выдать тип
     *
     * @param column
     * @return
     */
    @Override
    public Class getColumnClass(int column) {
        return columnClasses[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return contents[rowIndex][columnIndex];
    }

    /**
     * для каждого столбца выдать им
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * какие ячейки редактируемые
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
