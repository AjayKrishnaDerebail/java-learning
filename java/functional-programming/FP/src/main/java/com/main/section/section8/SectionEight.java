package com.main.section.section8;

import com.main.model.Student;
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

    StreamsExample.streamReduceExample(1.0, (a, b) -> a * b);

    log.info(LINE_SEPARATOR);

    StreamsExample.streamReduceHighestGpa();

    log.info(LINE_SEPARATOR);

    StreamsExample.filterMapReduceExample();

    log.info(LINE_SEPARATOR);

    StreamsExample.allMatchExample();

    log.info(LINE_SEPARATOR);

    StreamsExample.anyMatchExample();

    log.info(LINE_SEPARATOR);

    StreamsExample.noneMatchExample();
  }

}