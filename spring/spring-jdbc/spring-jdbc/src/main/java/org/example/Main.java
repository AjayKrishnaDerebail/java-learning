package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.StudentDao;

public class Main {
    public static void main (String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("studentconfig.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

//        Student student = new Student(6,"Abhay Kamath","Kudroli");
//        int result = studentDao.insertStudent(student);
//        System.out.println("Records inserted " +result);
//
//        Student studentUpdate = new Student(1,"Nikhil Kumar","Kumpala");
//        int resultUpdate = studentDao.updateStudent(studentUpdate);
//        System.out.println("Records updated " +resultUpdate);


          int resultDelete = studentDao.deleteStudent(1);
          System.out.println("Records deleted " +resultDelete);

    }
}