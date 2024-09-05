package com.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FirstServlet implements Servlet {

    ServletConfig config;

    @Override
    public void init(ServletConfig config) {
        this.config = config;
        System.out.println("Creating config");
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        System.out.println("Processing logic");
        resp.setContentType("text/html");
        PrintWriter out =  resp.getWriter();
        out.println("<h1> This is my output from servlet method </h1>");
        out.println("<h1> Today's date is " + new Date().toString() + "</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying servlet");
    }

    @Override
    public String getServletInfo() {
        return "This is servlet dummy info";
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

}