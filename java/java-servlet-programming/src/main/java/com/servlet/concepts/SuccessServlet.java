package com.servlet.concepts;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Cookie[] c = req.getCookies();
        if(c==null){
            throw new NullPointerException();
        }
        else{
            out.println("<h2>Successfully registered</h2>");
            String nameValue = URLDecoder.decode(c[0].getValue(), StandardCharsets.UTF_8.name());
            String passwordValue = URLDecoder.decode(c[1].getValue(), StandardCharsets.UTF_8.name());
            out.println("<div>Name is " +  nameValue + "</div>");
            out.println("<div>Password is " + passwordValue + "</div>");
        }
        out.println("<form action=\"goToRegistrationForm\" method=\"get\">");
        out.println("<button type=\"submit\">" +"Registration page" + "</button>");
        out.println("</form>");
    }
}
