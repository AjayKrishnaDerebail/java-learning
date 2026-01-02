package com.main.section.section7.supplier;

import com.main.model.Student;
import com.main.utility.StudentCache;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupplierDemo {

  public static void SupplierExercise() {

    List<Student> myLocalCache = new ArrayList<>();

    Supplier<List<Student>> databaseCall = () -> {
      log.info(">>> [DB] EXECUTING HEAVY SQL QUERY... (Slow)");
      return StudentCache.retrievalOperation();
    };

    log.info("--- Request 1 (Cache Empty) ---");
    myLocalCache = StudentCache.getStudentsFromCache(myLocalCache, databaseCall);

    log.info("\n--- Request 2 (Cache Full) ---");
    // This time, the lambda code inside 'databaseCall' will NOT run!
    StudentCache.getStudentsFromCache(myLocalCache, databaseCall);
  }

}