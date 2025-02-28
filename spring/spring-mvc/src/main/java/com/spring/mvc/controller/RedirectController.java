package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {
  public String firstUrl = "redirect:/two";
  private static final String redirectedPage = "redirectedPage";

  @RequestMapping("/one")
  public String one(){
    System.out.println("Using redirect prefix to redirect");
    return firstUrl;
  }

  @RequestMapping("/two")
  public RedirectView two(){
    System.out.println("Using redirect view to redirect");
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("three");
    return redirectView;
  }

  @RequestMapping("/three")
  public String three(){
    System.out.println("Redirected page");
    return redirectedPage;
  }
}
