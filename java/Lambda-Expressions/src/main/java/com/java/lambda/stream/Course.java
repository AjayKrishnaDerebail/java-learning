package com.java.lambda.stream;

public record Course(String courseCode, String title, int lectureCount) {

  public Course {
    if (lectureCount <= 0) {
      lectureCount = 1;
    }
  }

  public Course(String courseCode, String title) {
    this(courseCode, title, 1);
  }

  public String toString() {
    return "%s %s".formatted(courseCode, title);
  }
}
