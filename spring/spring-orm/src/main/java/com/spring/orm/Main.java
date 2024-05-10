package com.spring.orm;

import com.spring.orm.entities.Student;
import com.spring.orm.repositories.StudentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ormconfig.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        /* Interactive program below */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean start = true;
        while(start){
            System.out.println("Press 1 to add a student \nPress 2 to display all students\nPress 3 to display single student's detail");
            System.out.println("Press 4 to delete a student's detail \nPress 5 to update a student's detail \nPress 6 to exit");

            try{
                int input = Integer.parseInt(reader.readLine());

                switch(input){
                    case 1:
                        System.out.println("Enter student details now");

                        System.out.println("Enter student id");
                        int studentId = Integer.parseInt(reader.readLine());

                        System.out.println("Enter student name");
                        String studentName = reader.readLine();

                        System.out.println("Enter student city");
                        String studentCity = reader.readLine();

                        Student stud = new Student(studentId,studentName,studentCity);
                        studentDao.insert(stud);
                        System.out.println("** Student added **");
                        break;
                    case 2:
                        List<Student> students = studentDao.getAllStudents();
                        System.out.println("** All student details **");
                        for (Student i : students){
                            System.out.println("Student id " + i.getStudentId());
                            System.out.println("Student name " + i.getStudentName());
                            System.out.println("Student city " + i.getStudentCity());
                            System.out.println("\n******************\n");
                        }
                        break;
                    case 3:
                        System.out.println("Enter student id whose details you want to retrieve");
                        int id = Integer.parseInt(reader.readLine());
                        Student singleStudent = studentDao.getStudent(id);

                        System.out.println("Student id " + singleStudent.getStudentId());
                        System.out.println("Student name " + singleStudent.getStudentName());
                        System.out.println("Student city " + singleStudent.getStudentCity());

                        System.out.println("\n******************\n");
                        break;
                    case 4:
                        System.out.println("Enter student id whose details you want to delete");
                        int deleteId = Integer.parseInt(reader.readLine());

                        studentDao.deleteStudent(deleteId);
                        break;
                    case 5:
                        System.out.println("Enter student id whose details you want to update");
                        
                        break;
                    case 6:
                        System.out.println("Signing off");
                        start=false;
                        break;
                    default:
                        System.out.println("Please enter correct details");
                }

            }catch(Exception e){
                System.out.println("Invalid input retry with different number");
                System.out.println(e.getMessage());
            }
        }
    }
}