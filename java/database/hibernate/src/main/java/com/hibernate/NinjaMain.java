package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class NinjaMain {
    public static void main (String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(NarutoVerse.class).buildSessionFactory();
        NinjaClan ninjaClan=new NinjaClan("Uzumaki",false,"none","Hidden leaf");
        NarutoVerse v1= new NarutoVerse("Naruto Uzumaki","Child of prophecy",ninjaClan);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx;
            tx = session.beginTransaction();
            session.persist(v1);
            tx.commit(); // same as session.getTransaction().commit();
        }
    }
}
