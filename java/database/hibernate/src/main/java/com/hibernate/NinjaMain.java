package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class NinjaMain {
    public static void main (String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(NarutoVerse.class).buildSessionFactory();
        NinjaClan ninjaClan = new NinjaClan("Hyuga", true, "Byakugan", "Hidden leaf");
        NarutoVerse v1 = new NarutoVerse("Hinata", "kekeigenkai", ninjaClan);

        try (Session session = sessionFactory.openSession()) {
            /*Transaction tx;
            tx = session.beginTransaction();
            session.persist(v1);
            tx.commit(); // same as session.getTransaction().commit();
            */
            /*
               Hibernate Query language
            */
            String query = "from NarutoVerse where ninjaClan.hasEyeSpeciality is true and ninjaClan.clanName='Hyuga'";
            Query q1 = session.createQuery(query);

            List<NarutoVerse> n1 = q1.getResultList();

            for(NarutoVerse i : n1){
                System.out.println(i.getNinjaName() + " " +i.getNinjaClan().getClanName());
            }

            Transaction tx;
            tx = session.beginTransaction();
            Query deleteQuery =session.createQuery("delete from NarutoVerse where ninjaClan.clanName='Hyuga'");
            int r = deleteQuery.executeUpdate();
            tx.commit();
        }
    }
}
