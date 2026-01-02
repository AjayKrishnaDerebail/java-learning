package com.main.utility;

import com.main.model.Student;
import java.util.List;
import java.util.function.Supplier;

public class StudentCache {

  public static List<Student> retrievalOperation(){
    return StudentDatabase.getAllStudents();
  }
  public static List<Student> getStudentsFromCache(List<Student> cache,
      Supplier<List<Student>> dbSupplier) {
    if (!cache.isEmpty()) {
      return cache;
    }
    return dbSupplier.get();
  }

}