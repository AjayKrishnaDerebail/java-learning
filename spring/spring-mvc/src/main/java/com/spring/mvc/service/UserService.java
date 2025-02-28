package com.spring.mvc.service;

import com.spring.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.mvc.repositories.UserDao;

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public int saveUser(User user) {
    return this.userDao.saveUser(user);
  }

}
