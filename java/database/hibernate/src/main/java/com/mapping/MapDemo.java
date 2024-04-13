package com.mapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MapDemo {
    public static void main (String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        AnswerOneToOne a1=new AnswerOneToOne(1001,"Java is a programming language");
        QuestionOneToOne q1=new QuestionOneToOne(101,"What is Java",a1);
        AnswerOneToOne a2=new AnswerOneToOne(1002,"Hitman Blood Money");
        QuestionOneToOne q2=new QuestionOneToOne(102,"Which is the best Hitman game",a2);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx;
            tx = session.beginTransaction();
            session.persist(a1);
            session.persist(q1);
            session.persist(a2);
            session.persist(q2);
            tx.commit(); // same as session.getTransaction().commit();
        }
        sessionFactory.close();
    }
}
