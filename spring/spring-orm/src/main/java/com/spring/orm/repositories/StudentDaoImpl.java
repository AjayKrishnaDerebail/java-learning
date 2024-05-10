package com.spring.orm.repositories;

import com.spring.orm.entities.Student;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StudentDaoImpl implements StudentDao{
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public int insert(Student student){
        int numberOfRecords;
        numberOfRecords = (Integer)this.hibernateTemplate.save(student);
        return numberOfRecords;
    }

    @SuppressWarnings("unused")
    public HibernateTemplate getHibernateTemplate () {
        return hibernateTemplate;
    }

    @SuppressWarnings("unused")
    public void setHibernateTemplate (HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
