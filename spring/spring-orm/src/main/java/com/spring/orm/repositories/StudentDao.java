package com.spring.orm.repositories;

import com.spring.orm.entities.Student;

import java.util.List;

public interface StudentDao {

    int insert (Student student);

    void deleteStudent (int studentId);

    Student getStudent (int studentId);

    List<Student> getAllStudents ();

    void updateStudent (Student student);

}
