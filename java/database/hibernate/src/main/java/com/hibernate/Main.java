package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main (String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Student student = new Student(103, "Kane", "Washington DC");
        Address address = new Address();
        address.setStreet("Chinmaya colony , Hosabettu");
        address.setCity("Surathkal,Mangaluru");
        address.setOpen(true);
        address.setX(100.871);
        address.setAddedDate(new Date());


        try (Session session = sessionFactory.openSession()) {
            Transaction tx;
            tx = session.beginTransaction();
            //session.persist(student);
            session.persist(address);
            tx.commit(); // same as session.getTransaction().commit();
        }



    }
}