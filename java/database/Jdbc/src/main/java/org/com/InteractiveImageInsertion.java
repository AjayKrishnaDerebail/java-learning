package org.com;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InteractiveImageInsertion {

    public static void main (String[] args) {

        System.out.println("Hello world!");

        try {

            Connection conn = ConnectionProvider.getConnection();

            String insertQuery = "INSERT INTO studentimage(pic) values(?)";

            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                try (FileInputStream fis = new FileInputStream(file)) {
                    insertStatement.setBinaryStream(1, fis, fis.available());
                    insertStatement.executeUpdate();
                } catch (IOException ie) {
                    System.out.println(ie.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Success");
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }
}