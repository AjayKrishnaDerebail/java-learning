package com.mapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MapDemo {
    public static void main (String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        QuestionOneToOne q1 = new QuestionOneToOne();
        q1.setQuestionId(101);
        q1.setQuestion("What is Java ?");
        AnswerOneToOne a1 = new AnswerOneToOne();
        a1.setAnswerId(1001);
        a1.setAnswer("Java is a programming language");
        a1.setQuestionOneToOne(q1);
        q1.setAnswer(a1);

        QuestionOneToOne q2 = new QuestionOneToOne();
        q2.setQuestionId(102);
        q2.setQuestion("Which is the best Hitman game ?");
        AnswerOneToOne a2 = new AnswerOneToOne();
        a2.setAnswerId(1002);
        a2.setAnswer("Hitman Blood Money");
        a2.setQuestionOneToOne(q2);
        q2.setAnswer(a2);

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
