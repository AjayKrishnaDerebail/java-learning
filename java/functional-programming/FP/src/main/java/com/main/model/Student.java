package com.main.model;

import java.util.List;

public record Student(String firstName, String lastName, double gpa, List<String> hobbies) {}