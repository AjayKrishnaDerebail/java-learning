package com.spring.mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class User {

  public User() {

  }

  public User(String email, String userName, String password) {
    this.email = email;
    this.userName = userName;
    this.password = password;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private int id;
  @Getter
  @Setter
  private String userName;
  @Getter
  @Setter
  private String email;
  @Getter
  @Setter
  private String password;

}