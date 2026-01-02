package com.main.section.section8;

import com.main.model.Student;
import com.main.section.section7.supplier.SupplierDemo;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SectionEight {

  private static final String LINE_SEPARATOR = "-------------------------------------\n";

  public static void streams() {

    StreamsExample.simpleStreamExample();

    log.info(LINE_SEPARATOR);

    StreamsExample.streamsMapExample();

    log.info(LINE_SEPARATOR);

    StreamsExample.flatMapExample();

    log.info(LINE_SEPARATOR);

    StreamsExample.sortStudents(Comparator.comparing(Student::gpa));

    log.info(LINE_SEPARATOR);

  }
}
