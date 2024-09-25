package com.servlet.concepts.fowardandincludelastdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Successfully registered</h2>");
        Cookie[] c = req.getCookies();
        if(c==null){
            throw new NullPointerException();
        }
        else{
            out.println("<div>Name is " +  c[0].getValue() + "</div>");
            out.println("<div>Password is " + c[1].getValue() + "</div>");
        }
    }
}
