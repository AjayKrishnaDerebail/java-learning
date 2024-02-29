package org.com;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class InsertProgram {

    public static void main (String[] args) {

        System.out.println("Hello world!");

        try {
            Connection conn = ConnectionProvider.getConnection();

            String createQuery = "create table if not exists StudentDetails(StudentId int(20) primary key auto_increment , " +
                    "StudentName varchar(50) not null ," +
                    "StudentCity varchar(400))";

            //create query
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate(createQuery);
            }

            System.out.println("Table created");

            String insertQuery = "insert into StudentDetails(StudentName,StudentCity) values(?,?)";

            //Get PreparedStatement Object
            try (PreparedStatement statement = conn.prepareStatement(insertQuery)) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                //System.out.println("Enter Name of the Student");
                //String name = reader.readLine();
                //System.out.println("Enter name of the city");
                //String city = reader.readLine();
                //statement.setString(1, name);
                //statement.setString(2, city);

                //statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error is \n " + e.getMessage());
            }

            System.out.println("Insertion successful");

            System.out.println("Update operations ");

            String updateQuery = "update studentdetails set studentName=?,studentCity=? where studentid=? ";

            try (PreparedStatement statement = conn.prepareStatement(updateQuery)) {

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                //System.out.println("Enter the new name");
                //String name = br.readLine();

                //System.out.println("Enter the new city");
                //String city = br.readLine();

                //System.out.println("Enter the student id");
                //int id = Integer.parseInt(br.readLine());

                //statement.setString(1,name);
                //statement.setString(2,city);
                //statement.setInt(3,id);

                //statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error is \n " + e.getMessage());
            }

            String selectQuery = "select * from studentdetails";
            try (Statement stmt = conn.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(selectQuery);

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String city = resultSet.getString(3);

                    System.out.println("| " + id + " | " + name + " | " + city);

                }

            } catch (Exception e) {
                System.out.println("Exception is " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }
}