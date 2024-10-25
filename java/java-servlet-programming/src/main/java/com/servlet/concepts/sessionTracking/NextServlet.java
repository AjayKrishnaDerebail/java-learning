package com.servlet.concepts.sessionTracking;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class NextServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            String user = request.getParameter("user");
            String city = request.getParameter("city");
            out.println("<h1>" + "Your name is : " + user + "</h1>");
            out.println("<h1>" + "Your name is : " + city + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
