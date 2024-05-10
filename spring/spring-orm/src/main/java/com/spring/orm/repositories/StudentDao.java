package com.spring.orm.repositories;

import com.spring.orm.entities.Student;

import java.util.List;

public interface StudentDao {

    public int insert (Student student);

    public void deleteStudent (int studentId);

    public Student getStudent (int studentId);

    public List<Student> getAllStudents ();

    public void updateStudent (Student student);

}
