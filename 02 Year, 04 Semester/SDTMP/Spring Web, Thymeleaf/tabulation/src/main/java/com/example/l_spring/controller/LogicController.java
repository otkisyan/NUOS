package com.example.l_spring.controller;

import com.example.l_spring.entities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogicController {

    private Result[] values;
    private double[] yValues;
    private double[] xValues;
    private Logic logic;

    @Autowired
    public LogicController(Logic logic) {

        this.logic = logic;
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("start") double start, @RequestParam("end") double end,
                            @RequestParam("step") double step, @RequestParam("a") double a, Model model) {

        if (end < start) {

            throw new IllegalArgumentException();
        }

        xValues = logic.xValuesArrayFill(start, end, step);
        yValues = logic.yValuesArrayFill(xValues, a);
        values = logic.convertToResult(yValues, xValues);

        model.addAttribute("minY", logic.getMinElement(yValues));
        model.addAttribute("minX", logic.getMinElementArgument(yValues, xValues));
        model.addAttribute("maxY", logic.getMaxElement(yValues));
        model.addAttribute("maxX", logic.getMaxElementArgument(yValues, xValues));
        model.addAttribute("average", logic.getAverage(yValues));
        model.addAttribute("sum", logic.getSum(yValues));
        model.addAttribute("result", values);

        return "calculate";
    }

}
