package com.spring.mvc.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Student {
  private int id;
  private String name;
  private String city;
  private Date date;
  private List<String> courses;
  private String gender;
  private String type;
  private Address address;
}