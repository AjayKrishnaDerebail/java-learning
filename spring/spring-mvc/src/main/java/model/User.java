package model;

import lombok.Getter;
import lombok.Setter;

public class User {

  public User() {

  }

  public User(String email, String userName, String password) {
    this.email = email;
    this.userName = userName;
    this.password = password;
  }

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