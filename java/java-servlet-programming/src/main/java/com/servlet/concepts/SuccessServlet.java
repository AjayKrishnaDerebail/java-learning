package com.servlet.concepts;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Cookie[] c = req.getCookies();
        if(c==null){
            throw new NullPointerException();
        }
        else{
            out.println("<h2>Successfully registered</h2>");
            String nameValue = c[0].getValue();
            String passwordValue = c[1].getValue();
            out.println("<div>Name is " +  nameValue + "</div>");
            out.println("<div>Password is " + passwordValue + "</div>");
        }
        out.println("<form action=\"goToRegistrationForm\" method=\"get\">");
        out.println("<button type=\"submit\">" +"Registration page" + "</button>");
        out.println("</form>");
    }
}
