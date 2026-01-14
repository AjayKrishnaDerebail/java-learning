package com.main.model;

import com.main.utility.Gender;
import java.util.List;

public record Student(String firstName, String lastName, double gpa, String grade, Gender gender,
                      List<String> hobbies, int noteBooks) {

}