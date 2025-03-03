package com.spring.mvc.interceptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.servlet.HandlerInterceptor;

public class FileUploadInterceptor implements HandlerInterceptor {

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws IOException, ServletException {
    System.out.println("preHandle method is executed");
    String fileName = request.getPart("profile").getSubmittedFileName();
    if (fileName.contains("h")) {
      response.setContentType("text/html");
      response.getWriter().println("Invalid data");
      return false;
    }
    return true;
  }

  public boolean postHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws ServletException, IOException {
    System.out.println("postHandle  method is executed");
    String fileName = request.getPart("profile").getSubmittedFileName();
    if (response.isCommitted()) {
      System.out.println("Response is already committed, skipping postHandle");
    } else {
      System.out.println("postHandle called");
    }
    if (fileName.contains("k")) {
      System.out.println("You are a kaneanite");
    }
    request.setAttribute("fileName", fileName);
    return true;
  }

  public boolean afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws ServletException, IOException {
    if (response.isCommitted()) {
      System.out.println("Response is already committed, skipping afterCompletion");
    } else {
      System.out.println("afterCompletion called");
    }
    System.out.println("After completion method is executed");
    String fileName = request.getPart("profile").getSubmittedFileName();
    if (fileName.contains("a")) {
      System.out.println("Hello aaaaa");
    }
    return true;
  }
}
