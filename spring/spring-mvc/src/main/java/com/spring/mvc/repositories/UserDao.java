package com.spring.mvc.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.Setter;
import com.spring.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UserDao {

  @Autowired
  @Getter
  @Setter
  @PersistenceContext
  private EntityManager entityManager;


  @Transactional
  public int saveUser(User user) {
    entityManager.persist(user);
    return user.getId();
  }

}