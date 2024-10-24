package com.servlet.concepts.sessionTracking.UrlRewriting;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class InitialServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            String name = request.getParameter("userName");
            out.println("<h1>" + "Your name is : " + name + "</h1>");
            out.println("<a href='nextServlet?user="+name+"'> Go to second servlet  </a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
