package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Welcome to register Servlet");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Welcome to register Servlet </h1>");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String course = request.getParameter("userCourse");
        String condition = request.getParameter("condition");

        if(!Objects.isNull(condition)){
            if(condition.equals("checked")) {
                out.println("<div>Name is " + name + "</div>");
                out.println("<div>Password is " + password + "</div>");
                out.println("<div>email is " + email + "</div>");
                out.println("<div>Gender is " + gender + "</div>");
                out.println("<div>Course is " + course + "</div>");
            }
            // Request is forwarded to another endpoint itself the output above of form contents won't be displayed
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("success");
            requestDispatcher.forward(request,response);
        }
        else {
            out.println("<div>Please accept terms and condition</div>");
            //Demonstration of RequestDispatcher include() method
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("form.html");
            requestDispatcher.include(request, response);
        }
        out.println("<div><a href=\"form.html\">Click here to go back to form main screen</a> </div>");
    }
}
