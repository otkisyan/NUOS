package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run(){
        String inputFilePath = "/Users/otkisyan/IdeaProjects/regex/input.txt";
        CelsiusTemperatureParser celsiusTemperatureParser = new CelsiusTemperatureParser();
        List<String> temperatures = celsiusTemperatureParser.extractTemperatures(inputFilePath);
        System.out.println(temperatures);
    }
}