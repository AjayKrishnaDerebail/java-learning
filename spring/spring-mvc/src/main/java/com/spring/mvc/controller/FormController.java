package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {

  @RequestMapping("/complexForm")
  public String showForm() {
    return "complexForm";
  }

  @RequestMapping(name = "handleComplexForm",method = RequestMethod.POST)
  public String handleForm() {
    return "success";
  }
}