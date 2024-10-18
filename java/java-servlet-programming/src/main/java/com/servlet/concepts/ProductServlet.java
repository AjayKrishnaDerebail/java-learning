package com.servlet.concepts;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("Welcome to product Servlet");
    String n1 = request.getParameter("n1");
    String n2 = request.getParameter("n2");
    int p=0;
      if (!n1.isEmpty() && !n2.isEmpty()) {
          try {
              int nn1 = Integer.parseInt(n1);
              int nn2 = Integer.parseInt(n2);
              p = nn1 * nn2;
          } catch (NumberFormatException e) {
              System.out.println("Invalid number format.");
          }
      }
    request.setAttribute("prod", p);

    System.out.println(request.getAttribute("prod"));

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("f1");
    requestDispatcher.forward(request, response);
  }
}
