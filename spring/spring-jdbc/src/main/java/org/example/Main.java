package org.example;

import entities.Address;
import entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.StudentDao;
import repositoryannotation.AddressDao;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String RECORDS_INSERTED = "Records inserted ";
    private static final String RECORDS_DELETED = "Records deleted ";
    private static final String RECORDS_UPDATED = "Records updated ";
    public static void main (String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("studentconfig.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        Student student = new Student(6, "Abhay Kamath", "Kudroli");
        try {
            int result = studentDao.insertStudent(student);
            System.out.println(RECORDS_INSERTED + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Student studentUpdate = new Student(1, "Nikhil Kumar", "Kumpala");
        try {
            int resultUpdate = studentDao.updateStudent(studentUpdate);
            System.out.println(RECORDS_UPDATED + resultUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int resultDelete = studentDao.deleteStudent(1);
            System.out.println(RECORDS_DELETED + resultDelete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ApplicationContext contextAnnotation = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        AddressDao addressDao = contextAnnotation.getBean(AddressDao.class);

        addressDao.create();
        //creating address table

        Address address = new Address(1, "24-3-250", "Attavar", "Mangalore", "Karnataka");

        try {
            int result = addressDao.insert(address);
            System.out.println(RECORDS_INSERTED + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Address address1 = new Address(2, "14-3-250", "Kapikad", "Mangalore", "Karnataka");
        Address address2 = new Address(3, "14-3-950", "Bejai", "Mangalore", "Karnataka");

        List<Address> addressesList = new ArrayList<>();
        addressesList.add(address1);
        addressesList.add(address2);

        try {
            int resultMultiple = addressDao.insertMultiple(addressesList);
            System.out.println(RECORDS_INSERTED + resultMultiple);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Address updateCityRecordOne = new Address(1, "24-3-250", "Attavar", "Mangaluru", "Karnataka");

        try {
            int resultUpdateRecordOne = addressDao.update(updateCityRecordOne);
            System.out.println(RECORDS_UPDATED + resultUpdateRecordOne);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            int resultDeleteAddress = addressDao.delete(3);
            System.out.println(RECORDS_DELETED + resultDeleteAddress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}