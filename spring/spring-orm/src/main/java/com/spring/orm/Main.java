package com.spring.orm;

import com.spring.orm.entities.Student;
import com.spring.orm.repositories.StudentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static final String STAR_CHARACTER_IN_TERMINAL = "\n******************\n";

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("ormconfig.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        /* Interactive program below */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean start = true;

        while (start) {

            System.out.println("Press 1 to add a student \nPress 2 to display all students\nPress 3 to display single student's detail");
            System.out.println("Press 4 to delete a student's detail \nPress 5 to update a student's detail \nPress 6 to exit");
            System.out.println(STAR_CHARACTER_IN_TERMINAL);

            try {

                int input = Integer.parseInt(reader.readLine());
                switch (input) {

                    case 1 -> {
                        System.out.println("Enter student details now");

                        System.out.println("Enter student id");
                        int studentId = Integer.parseInt(reader.readLine());
                        System.out.println("Enter student name");
                        String studentName = reader.readLine();
                        System.out.println("Enter student city");
                        String studentCity = reader.readLine();

                        Student stud = new Student(studentId, studentName, studentCity);
                        studentDao.insert(stud);

                        System.out.println("** Student added **");
                    }
                    case 2 -> {
                        List<Student> students = studentDao.getAllStudents();
                        System.out.println("** All student details **");

                        for (Student i : students) {
                            System.out.println("Student id " + i.getStudentId());
                            System.out.println("Student name " + i.getStudentName());
                            System.out.println("Student city " + i.getStudentCity());
                            System.out.println(STAR_CHARACTER_IN_TERMINAL);
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter student id whose details you want to retrieve");

                        int id = Integer.parseInt(reader.readLine());
                        Student singleStudent = studentDao.getStudent(id);

                        System.out.println("Student id " + singleStudent.getStudentId());
                        System.out.println("Student name " + singleStudent.getStudentName());
                        System.out.println("Student city " + singleStudent.getStudentCity());
                        System.out.println(STAR_CHARACTER_IN_TERMINAL);
                    }
                    case 4 -> {
                        System.out.println("Enter student id whose details you want to delete");
                        int deleteId = Integer.parseInt(reader.readLine());
                        studentDao.deleteStudent(deleteId);
                    }
                    case 5 -> {
                        System.out.println("Enter student id whose details you want to update");

                        int updateId = Integer.parseInt(reader.readLine());
                        Student updateStudent = studentDao.getStudent(updateId);

                        System.out.println("Details of student that you have retrieved");
                        System.out.println("Student id " + updateStudent.getStudentId());
                        System.out.println("Student name " + updateStudent.getStudentName());
                        System.out.println("Student city " + updateStudent.getStudentCity());

                        System.out.println("What do you want to update both name and city , or either one?");
                        System.out.println("To update name type N\nTo update city type C\nTo update both type B");

                        String userUpdateChoice = reader.readLine().toUpperCase();
                        char userUpdateChoiceChar = userUpdateChoice.charAt(0);
                        switch (userUpdateChoiceChar) {
                            case 'N' -> {
                                System.out.println("Enter the new name");
                                String newName = reader.readLine();

                                updateStudent.setStudentName(newName);
                                studentDao.updateStudent(updateStudent);
                            }
                            case 'C' -> {
                                System.out.println("Enter the new city");
                                String newCity = reader.readLine();

                                updateStudent.setStudentCity(newCity);
                                studentDao.updateStudent(updateStudent);
                            }
                            case 'B' -> {
                                System.out.println("Enter the new name and city");
                                String newName = reader.readLine();
                                String newCity = reader.readLine();

                                updateStudent.setStudentName(newName);
                                updateStudent.setStudentCity(newCity);

                                studentDao.updateStudent(updateStudent);
                            }
                            default -> System.out.println("Please enter correct character");
                        }
                    }
                    case 6 -> {
                        System.out.println("Give up on your dreams and die");
                        start = false;
                    }
                    default -> System.out.println("Please enter correct choice");
                }

            } catch (Exception e) {
                System.out.println("Invalid input retry with different number");
                System.out.println(e.getMessage());
            }
        }
    }
}