package com.servlet.concepts;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class FinalServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");

    PrintWriter out = response.getWriter();
    int sum = (int) request.getAttribute("sum");
    int prod = (int) request.getAttribute("prod");

    if (sum == 0 || prod == 1) {
      out.println("You did not input any value please go back to initial sum screen");
    } else {
      out.println("<h1>Sum is = " + sum + "</h1>");
      out.println("<h1>Product is = " + prod + "</h1>");
      out.println(request.getAttribute("notEmpty"));
    }

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("sum.html");
    out.println("<form action=\"sumForm\" method=\"post\">");
    out.println("<button type=\"submit\">" +"sum page" + "</button>");
    out.println("</form>");
    requestDispatcher.include(request, response);
  }
}
