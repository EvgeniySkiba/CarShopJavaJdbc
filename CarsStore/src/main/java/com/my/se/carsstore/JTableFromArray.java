/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.se.carsstore;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Skiba
 */
public class JTableFromArray {
    
    private static final  int HEIGHT = 200;
    private static final int WEIGHT = 400;
    public static void main(String [] args){
        
        // массив с названиями для таблицы 
        Object [] columnHeader =  {"Имя", "Фамилия", "Отчество"};
        
        // массив с данными для таблицы 
        Object [][] tableData = {
            {"Иванов","Иван","Иваныч"},
            {"Петров","Олег","Петрович"},
            {"Aлексеев","Алексей","Алексеевич"},
            {"Смирнов2","Павел","Яковлевич"},
            {"Смирнов3","Павел","Яковлевич"},
            {"Смирнов4","Павел","Яковлевич"},
            {"Смирнов5","Павел","Яковлевич"},
            {"Смирнов6","Павел","Яковлевич"},
            {"Смирнов7","Павел","Яковлевич"},
            {"Смирнов8","Павел","Яковлевич"},
            {"Смирнов9","Павел","Яковлевич"}
        };
        
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                   
               
        JFrame frame = new JFrame("JTableExample");
        frame.getContentPane().setLayout(new FlowLayout());
        
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTable jTableFio = new JTable(tableData,columnHeader);
        JScrollPane scrol = new JScrollPane(jTableFio);
       
        
        //размеры прокручиваемой области 
        jTableFio.setPreferredScrollableViewportSize(new Dimension(400,200));
        
        frame.getContentPane().add(scrol);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
               }
         });
    }
}
