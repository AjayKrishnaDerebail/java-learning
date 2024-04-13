package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchDemo {
    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Student st1 = session.get(Student.class , 102);
        System.out.println(st1);
        System.out.println(st1);//only one database call fetches from session cache

        Student st2 = session.load(Student.class ,101);
        System.out.println(st2);
        System.out.println(st2);

        /*
        Subsequent calls to load() with the same identifier within the same
        session will return the same proxy object, utilizing the session cache when appropriate.
         */

        session.close();
        factory.close();

    }
}
