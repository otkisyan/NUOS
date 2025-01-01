package org.example.servletsjsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import org.example.servletsjsp.logic.SinhCalculator;
import org.example.servletsjsp.model.sinh.SinhResult;

@WebServlet(name = "sinhServlet", value = "/sinh-servlet")
public class SinhCalculateServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    double x = Double.parseDouble(request.getParameter("x"));
    int n = Integer.parseInt(request.getParameter("n"));
    double e = Double.parseDouble(request.getParameter("e"));

    SinhCalculator calculator = new SinhCalculator(x, n, e);

    request.setAttribute(
        "sinhResult",
        new SinhResult(
            calculator.calculateSum(), calculator.exactSinh(), calculator.calculateSumWithE()));
    request.setAttribute("x", x);
    request.setAttribute("n", n);
    request.setAttribute("e", e);
    RequestDispatcher dispatcher = request.getRequestDispatcher("sinh.jsp");
    dispatcher.forward(request, response);
  }

  public void destroy() {}
}
