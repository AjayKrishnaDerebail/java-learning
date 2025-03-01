package com.spring.mvc.controller;

import com.spring.mvc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {

  @RequestMapping("/complexForm")
  public String showForm() {
    return "complexForm";
  }

  @RequestMapping(value = "handleComplexForm",method  = RequestMethod.POST)
  public String handleForm(@ModelAttribute("student") Student student) {
    System.out.println(student);
    System.out.println(student.getAddress());
    return "successStudent";
  }
}