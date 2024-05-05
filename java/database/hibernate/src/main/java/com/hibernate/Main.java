package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class Main {
    public static void main (String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Student student = new Student(103, "Kane", "Washington DC");
        Student student1 = new Student(104, "Steve Austin", "Texas");
        Student student2 = new Student(105, "Undertaker", "Death Valley");
        Student student3 = new Student(106, "Khali", "Punjab");
        Address address = new Address();
        address.setStreet("Chinmaya colony , Hosabettu");
        address.setCity("Surathkal,Mangaluru");
        address.setOpen(true);
        address.setX(100.871);
        address.setAddedDate(new Date());


        try (Session session = sessionFactory.openSession()) {
            Transaction tx;
            tx = session.beginTransaction();
            try {
                session.persist(student);
                session.persist(student1);
                session.persist(student2);
                session.persist(student3);
                session.persist(address);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            tx.commit(); // same as session.getTransaction().commit();

            Student s1 = session.get(Student.class, 103);
            System.out.println(s1);
        }

        try (Session session1 = sessionFactory.openSession()) {
            Student s1 = session1.get(Student.class, 103);
            System.out.println(s1);
        }

        sessionFactory.close();
    }
}