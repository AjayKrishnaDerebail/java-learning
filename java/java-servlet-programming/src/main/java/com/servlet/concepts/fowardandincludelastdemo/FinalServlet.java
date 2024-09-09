package com.servlet.concepts.fowardandincludelastdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class FinalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        int sum = (int) request.getAttribute("sum");
        int prod = (int) request.getAttribute("prod");

        out.println("<h1>Sum is = " + sum + "</h1>");
        out.println("<h1>Product is = " + prod + "</h1>");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("sum.html");
        requestDispatcher.include(request, response);

    }
}
