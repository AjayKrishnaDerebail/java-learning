package com.spring.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class SearchController {
  @RequestMapping("/searchApp")
  public String searchHandler(){
    return "searchPage";
  }

  @RequestMapping(value = "/searchGoogle",method = RequestMethod.POST)
  public RedirectView search(@RequestParam("queryBox") String queryBox){
    final RedirectView redirectView = new RedirectView();
    String url = "https://www.google.com/search?q="+queryBox;
    String nullString = null;
    //Throwing exception intentionally
    System.out.println(nullString.length());
    redirectView.setUrl(url);
    return redirectView;
  }

  @RequestMapping("/goBackToIndex")
  public String goBack(){
    return "index";
  }

  /**
    To handle null pointer exception
    To handle any exception you can use Exception class
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({NullPointerException.class})
  public String exceptionHandlerForNPE(Model model){
    model.addAttribute("nullException","Null pointer exception occurred");
    return "nullPage";
  }
}
