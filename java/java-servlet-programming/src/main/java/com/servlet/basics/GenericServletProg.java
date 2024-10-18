package com.servlet.basics;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class GenericServletProg extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        System.out.println("This is generic servlet");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("This is generic servlet web output");
        out.println("<form action=\"index\" method=\"get\">");
        out.println("<button type=\"submit\">" +"Index page" + "</button>");
        out.println("</form>");
    }
}
