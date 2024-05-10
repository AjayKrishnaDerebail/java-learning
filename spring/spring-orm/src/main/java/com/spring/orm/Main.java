package com.spring.orm;

import com.spring.orm.entities.Student;
import com.spring.orm.repositories.StudentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main (String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ormconfig.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        Student student = new Student(1, "Eren", "Shiganshina");
        try {
            int result = studentDao.insert(student);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}