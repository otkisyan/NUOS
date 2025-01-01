package org.example.servletsjsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.servletsjsp.logic.TabulationCalculator;
import org.example.servletsjsp.model.tabulation.TabulationResult;
import org.example.servletsjsp.validation.tabulation.NumberFormatHandler;
import org.example.servletsjsp.validation.tabulation.RangeCheckHandler;
import org.example.servletsjsp.validation.tabulation.StepZeroCheckHandler;
import org.example.servletsjsp.validation.tabulation.TabulationValidationHandler;

@WebServlet(name = "tabulationServlet", value = "/tabulation-servlet")
public class TabulationCalculateServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    boolean success = true;
    double startValue = 0;
    double endValue = 0;
    double stepValue = 0;

    try {

      startValue = Double.parseDouble(request.getParameter("x1"));
      endValue = Double.parseDouble(request.getParameter("x2"));
      stepValue = Double.parseDouble(request.getParameter("step"));

      // Chain of Responsibility
      TabulationValidationHandler numberFormatHandler = new NumberFormatHandler();
      TabulationValidationHandler rangeCheckHandler = new RangeCheckHandler();
      TabulationValidationHandler stepZeroCheckHandler = new StepZeroCheckHandler();
      numberFormatHandler.setNextHandler(rangeCheckHandler);
      rangeCheckHandler.setNextHandler(stepZeroCheckHandler);
      numberFormatHandler.handle(startValue, endValue, stepValue);

    } catch (NumberFormatException err) {

      success = false;
      response.setStatus(400);
      request.getRequestDispatcher("tabulation.jsp").forward(request, response);

    } catch (IllegalArgumentException err) {

      success = false;
      response.setStatus(400);
      request.getRequestDispatcher("tabulation.jsp").forward(request, response);
    }

    if (success) {

      TabulationCalculator calculator = new TabulationCalculator(startValue, endValue, stepValue);

      request.setAttribute(
          "tabulationResult",
          new TabulationResult(
              calculator.getMinElement(),
              calculator.getMinElementArgument(),
              calculator.getMaxElement(),
              calculator.getMaxElementArgument(),
              calculator.getAverage(),
              calculator.getSum(),
              calculator.getPoints()));
      request.getRequestDispatcher("tabulation.jsp").forward(request, response);
    }
  }
}
