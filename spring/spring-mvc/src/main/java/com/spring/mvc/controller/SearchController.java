package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    //Throwing exception intentionally
    redirectView.setUrl(url);
    return redirectView;
  }

  @RequestMapping("/goBackToIndex")
  public String goBack(){
    return "index";
  }

  @RequestMapping("/exceptionPage")
  public void throwingExceptionMethod(){
    String nullString = null;
    System.out.println(nullString.length());
  }
}