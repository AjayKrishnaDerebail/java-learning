package com.servlet.concepts.jspbasics;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class FilterExample implements Filter {
  @Override
  public void destroy() {
    Filter.super.destroy();
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
      System.out.println("Filter started");
      String n1=request.getParameter("n1");
      String n2=request.getParameter("n2");

      if(!n1.isEmpty() && !n2.isEmpty()){
          System.out.println("n1 and n2 is not empty");
          request.setAttribute("notEmpty", false);
      }
      chain.doFilter(request, response);
      System.out.println("Filter ended");
  }
}
