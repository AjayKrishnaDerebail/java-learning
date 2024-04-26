package com.mapping.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ManyToManyMapingDemo {
    public static void main (String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Employee e1 = new Employee();
        Employee e2 = new Employee();

        e1.setEid(34);
        e1.setEname("Abhay");

        e2.setEid(35);
        e2.setEname("Abhishek");

        Project p1 = new Project();
        Project p2 = new Project();

        p1.setPid(301);
        p1.setPname("Library management system");

        p2.setPid(302);
        p2.setPname("ChatBot");

        List<Employee> employeeList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        employeeList.add(e1);
        employeeList.add(e2);

        projectList.add(p1);
        projectList.add(p2);

        e1.setProjectList(projectList);
        p2.setEmployeeList(employeeList);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx;
            tx = session.beginTransaction();

            session.persist(e1);
            session.persist(e2);
            session.persist(p1);
            session.persist(p2);

            tx.commit();
        }

        sessionFactory.close();
    }
}
