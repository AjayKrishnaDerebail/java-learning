package com.mapping.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneToManyMappingDemo {
    public static void main (String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        QuestionOneToMany qm1 = new QuestionOneToMany();
        qm1.setQuestionId(201);
        qm1.setQuestion("Who is the best WWE wrestler?");

        AnswerOneToMany am1 = new AnswerOneToMany();
        am1.setAnswerId(2001);
        am1.setAnswer("Undertaker");
        am1.setQuestionOneToMany(qm1);

        AnswerOneToMany am2 = new AnswerOneToMany();
        am2.setAnswerId(2002);
        am2.setAnswer("The Rock");
        am2.setQuestionOneToMany(qm1);

        AnswerOneToMany am3 = new AnswerOneToMany();
        am3.setAnswerId(2003);
        am3.setAnswer("Stone Cold Steve Austin");
        am3.setQuestionOneToMany(qm1);

        List<AnswerOneToMany> list1 = new ArrayList<>();
        list1.add(am1);
        list1.add(am2);
        list1.add(am3);

        qm1.setAnswers(list1);

        try (Session session = sessionFactory.openSession()) {

            Transaction tx;
            tx = session.beginTransaction();
            try {
                session.persist(am1);
                session.persist(am2);
                session.persist(am3);
                session.persist(qm1);
                tx.commit(); /* same as session.getTransaction().commit();*/
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            QuestionOneToMany q = session.get(QuestionOneToMany.class, 201);

            System.out.println(q.getQuestion());

            /*for (AnswerOneToMany a : q.getAnswers()) {
                System.out.println(a.getAnswer());
            }*/

            Query q3 = session.createQuery("select q.question , a.answer from QuestionOneToMany as q inner join q.answers as a");
            List<Object[]> l3 = q3.getResultList();

            for(Object[] i : l3){
                System.out.println(Arrays.toString(i));
            }

        }
        sessionFactory.close();
    }
}
