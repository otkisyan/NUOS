package com.tabulation.l11;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "logicServlet", value = "/logic-servlet")
public class LogicServlet extends HttpServlet {

    private double[] yValues;
    private double[] xValues;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logic logic = new Logic();

        boolean success = true;
        int indexY = 0;
        int indexX = 0;

        try {

            indexY = Integer.parseInt(request.getParameter("indexY"));
            indexX = Integer.parseInt(request.getParameter("indexX"));

        } catch (NumberFormatException err) {

            success = false;
            response.setStatus(400);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        if (success) {

            double valueY = 0;
            double valueX = 0;

            try {

                valueY = logic.getValueByIndex(yValues, indexY);
                valueX = logic.getValueByIndex(xValues, indexX);

            } catch (IllegalArgumentException err) {

                success = false;
                request.setAttribute("error", "Такого індексу не існує");
                response.setStatus(400);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            if (success) {

                request.setAttribute("valueByIndexY", valueY);
                request.setAttribute("valueByIndexX", valueX);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logic logic = new Logic();

        boolean success = true;
        double startValue = 0;
        double endValue = 0;
        double stepValue = 0;
        double a = 0;

        try {

            startValue = Double.parseDouble(request.getParameter("x1"));
            endValue = Integer.parseInt(request.getParameter("x2"));
            stepValue = Double.parseDouble(request.getParameter("step"));
            a = Double.parseDouble(request.getParameter("a"));

            if (endValue < startValue) {

                throw new IllegalArgumentException();
            }

        } catch (NumberFormatException err) {

            success = false;
            response.setStatus(400);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (IllegalArgumentException err) {

            success = false;
            response.setStatus(400);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        if (success) {

            xValues = logic.xValuesArrayFill(startValue, endValue, stepValue);
            yValues = logic.yValuesArrayFill(xValues, a);

            request.setAttribute("xValues", xValues);
            request.setAttribute("yValues", yValues);
            request.setAttribute("minY", logic.getMinElement(yValues));
            request.setAttribute("minX", logic.getMinElementArgument(yValues, xValues));
            request.setAttribute("maxY", logic.getMaxElement(yValues));
            request.setAttribute("maxX", logic.getMaxElementArgument(yValues, xValues));
            request.setAttribute("average", logic.getAverage(yValues));
            request.setAttribute("sum", logic.getSum(yValues));

            // Когда вы вызываете request.getRequestDispatcher("index.jsp"),
            // он возвращает объект RequestDispatcher, который может перенаправить запрос на файл index.jsp.
            // Затем, когда вы вызываете forward(request, response) на объекте RequestDispatcher,
            // он отправляет объекты запроса и ответа в файл index.jsp,
            // позволяя JSP-странице получить доступ к атрибутам, заданным в запросе,
            // и сгенерировать HTML-вывод на основе этих атрибутов.
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    public void destroy() {

    }
}