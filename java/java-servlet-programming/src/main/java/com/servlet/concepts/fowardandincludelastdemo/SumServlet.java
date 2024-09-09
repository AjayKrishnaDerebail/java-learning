package com.servlet.concepts.fowardandincludelastdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Welcome to Sum Servlet");

        String n1 = request.getParameter("n1");
        String n2 = request.getParameter("n2");

        int nn1 = Integer.parseInt(n1);
        int nn2 = Integer.parseInt(n2);

        int s = nn1 + nn2;

        request.setAttribute("sum", s);

        System.out.println(request.getAttribute("sum"));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("p1");
        requestDispatcher.forward(request, response);
    }
}

