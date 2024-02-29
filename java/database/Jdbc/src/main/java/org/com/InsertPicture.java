package org.com;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertPicture {

    public static void main (String[] args) {

        System.out.println("Hello world!");
        try {
            Connection conn = ConnectionProvider.getConnection();

            //String alterQuery = "alter table studentimage modify pic longblob";

            //try(PreparedStatement alterStatement = conn.prepareStatement(alterQuery)) {
            //    alterStatement.executeUpdate();
            //}
            //System.out.println("Table column data-type altered");

            String insertQuery = "insert into studentimage(pic) values(?)";

            try (PreparedStatement imageStatement = conn.prepareStatement(insertQuery)) {
                String imagePath = "C:\\Users\\ajayk\\OneDrive\\Desktop\\kane.jpg";
                try (FileInputStream image = new FileInputStream(imagePath)) {
                    imageStatement.setBinaryStream(1, image, image.available());
                }
                //imageStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Exception is " + e.getMessage());
            }
            System.out.println("Image inserted");

            //String deleteQuery = "delete from studentimage where id=1";

            //try(PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)) {
            //deleteStatement.executeUpdate();
            //}

            //System.out.println("Image deleted");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}