/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.se.carsstore;

import com.my.se.tablemodels.TableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Skiba
 */
public class Application {
    public static void main(String [ ] args)
    {
        
        Connection connection = SqlLiteConnection.getConnection();
        TableModel model;
        try {
            model = new TableModel(connection, "carBrandView");

            JTable jtable = new JTable(model);

            jtable.setDefaultRenderer(Object.class, new TableRenderer());
            JScrollPane scroller = new JScrollPane(jtable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
            JFrame frame = new JFrame("Загрузка данных в JTable");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(scroller);
            frame.pack();
            frame.setVisible(true);
            
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Application here");       
    }
}
