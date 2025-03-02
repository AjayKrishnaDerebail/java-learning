package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String home(final Model model){
        System.out.println("Inside home method of controller");
        System.out.println("This returns default view of index.jsp");
        model.addAttribute("message", "Welcome to Spring MVC");

        return "index";
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about(){
        System.out.println("Inside about method of controller");
        return "about";
    }

    @RequestMapping(path = "/help" ,method = RequestMethod.GET)
    public ModelAndView help(){
        System.out.println("Inside help method of controller");
        ModelAndView mv = new ModelAndView();
        mv.addObject("messageFromHelp", ": This is help page of Spring MVC");
        mv.setViewName("help");
        return mv;
    }

    @RequestMapping("/user/{userId}/{userName}")
    public String user(@PathVariable("userId") int userId,
        @PathVariable("userName") String userName, Model model) {
        System.out.println("Inside user method of controller");
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        System.out.println(userId);
        System.out.println(userName);
        return "index";
    }
}