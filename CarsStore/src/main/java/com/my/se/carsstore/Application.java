/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.se.carsstore;

import com.my.se.tablemodels.TableModel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Skiba
 */
public class Application {

    public static void main(String[] args) {

        Connection connection = SqlLiteConnection.getConnection();
        TableModel model;
        try {
            model = new TableModel(connection, "carBrandView");

            
            JButton button = new JButton("Refresh");
            button.setSize(100,50);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(".actionPerformed()"); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            
            
            JTable jtable = new JTable(model);

            jtable.setDefaultRenderer(Object.class, new TableRenderer());
            JScrollPane scroller = new JScrollPane(jtable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JFrame frame = new JFrame("Загрузка данных в JTable");
            

            JMenuBar menuBar = new JMenuBar();
            Font font = new Font("Verdana", Font.PLAIN, 11);

            JMenu fileMenu = new JMenu("File");
            fileMenu.setFont(font);

            JMenu newMenu = new JMenu("New");
            newMenu.setFont(font);
            fileMenu.add(newMenu);

            JMenuItem txtFileItem = new JMenuItem("Text file");
            txtFileItem.setFont(font);
            newMenu.add(txtFileItem);

            JMenuItem imgFileItem = new JMenuItem("Image file");
            imgFileItem.setFont(font);
            newMenu.add(imgFileItem);

            JMenuItem folderItem = new JMenuItem("Folder");
            folderItem.setFont(font);
            newMenu.add(folderItem);

            JMenuItem openItem = new JMenuItem("Open");
            openItem.setFont(font);
            fileMenu.add(openItem);

            JMenuItem closeItem = new JMenuItem("Close");
            closeItem.setFont(font);
            fileMenu.add(closeItem);

            JMenuItem closeAllItem = new JMenuItem("Close all");
            closeAllItem.setFont(font);
            fileMenu.add(closeAllItem);

            fileMenu.addSeparator();

            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.setFont(font);
            fileMenu.add(exitItem);
            
            exitItem.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {               
                //TODO : implement: close connection
                System.exit(0);             
            }           
        });
       
            menuBar.add(fileMenu);             
            frame.setJMenuBar(menuBar);
            
           // frame.add(button);
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
