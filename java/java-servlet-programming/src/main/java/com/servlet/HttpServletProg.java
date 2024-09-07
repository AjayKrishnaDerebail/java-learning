package com.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletProg extends HttpServlet {
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        System.out.println("This is get method");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>This is from doGet method </h1>");
        out.println("<div> <a href=\"index.html\">Click here to go back to index</a> </div>");
    }
}
