package com.spring.orm.repositories;

import com.spring.orm.entities.Student;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao {
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public int insert(Student student) {
        int numberOfRecords;
        numberOfRecords = (Integer) this.hibernateTemplate.save(student);
        return numberOfRecords;
    }

    @Override
    public Student getStudent(int studentId) {
        return this.hibernateTemplate.get(Student.class, studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.hibernateTemplate.loadAll(Student.class);
    }

    @Override
    @Transactional
    public void deleteStudent(int studentId) {
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        if (student != null) {
            this.hibernateTemplate.delete(student);
            System.out.println("Student deleted");
        } else {
            System.out.println("Incorrect id given");
        }
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        this.hibernateTemplate.update(student);
    }


    @SuppressWarnings("unused")
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @SuppressWarnings("unused")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
