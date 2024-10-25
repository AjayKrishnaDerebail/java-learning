package com.servlet.concepts.sessionTracking;

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
            String city = request.getParameter("userCity");
            out.println("<h1>" + "Your name is : " + name + "</h1>");
            out.println("<h1>" + "Your city is : " + city + "</h1>");
            out.println("<a href='nextServlet?user=" + name + "&city=" + city + "'> Go to second servlet </a>");
            out.println(
                    "<form action='nextServlet' method='get'>" +
                    "<input type='hidden' name='user' value='"+name+"'/>" +
                    "<input type='hidden' name='city' value='"+city+"'/>" +
                    "<button>Hidden form</button>" +
                    "</form>"
                    );
            out.println("</body>");
            out.println("</html>");
        }
    }
}
