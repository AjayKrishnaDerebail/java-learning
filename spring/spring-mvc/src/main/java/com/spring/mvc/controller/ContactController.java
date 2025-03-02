package com.spring.mvc.controller;

import com.spring.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.mvc.service.UserService;

@Controller
public class ContactController {

  @Autowired
  private UserService userService;

  @ModelAttribute
  public void addCommonData(Model model) {
    model.addAttribute("helpSection", "Contact Us");
  }

  @RequestMapping(path = "contact", method = RequestMethod.GET)
  @ModelAttribute
  public String showRegistrationForm(Model model) {
    model.addAttribute("headerForContactPage", "Contact Registration Page");
    return "contact";
  }

  @RequestMapping(path = "/complexForm" ,method = RequestMethod.POST)
  public String contact(){
    System.out.println("Inside contact method of controller");
    return "complexForm";
  }

  @RequestMapping(path = "processForm", method = RequestMethod.POST)
  public String formProcess(@ModelAttribute User user , Model model) {

    System.out.println(user);
    int createdUser = this.userService.saveUser(user);
    model.addAttribute("successMessage", "User created with id {} " + createdUser);
    return "success";
  }

/*
    public String formProcess (HttpServletRequest request) {
        System.out.println(email);
        System.out.println(userName);
        System.out.println(password);
        model.addAttribute("email",email);
        model.addAttribute("userName",userName);
        model.addAttribute("password",password);
        You had to use HttpServletRequest object in servlets and use
        request.getParameter("email");
        request.getParameter("userName");
        and so on .
        You had to map the servlet as well in a config file
        return "success";
    }

    Simplified to

     @RequestMapping(path="processForm",method = RequestMethod.POST)
     public String formProcess (@RequestParam(name = "email", required=true) String email,
                               @RequestParam(name ="userName", required=false) String userName,
                               @RequestParam("password") String password , Model model) {
        System.out.println(email);
        System.out.println(userName);
        System.out.println(password);
        model.addAttribute("email",email);
        model.addAttribute("userName",userName);
        model.addAttribute("password",password);

        return "success";
    }

    Further simplified to

    @RequestMapping(path = "processForm", method = RequestMethod.POST)
    public String formProcess (@RequestParam(name = "email") String email,
                               @RequestParam(name = "userName", required = false) String userName,
                               @RequestParam("password") String password, Model model) {
        User user = new User(userName, email, password);
        model.addAttribute("user", user);

        return "success";
    }

    Final simplified code written on top
*/


}