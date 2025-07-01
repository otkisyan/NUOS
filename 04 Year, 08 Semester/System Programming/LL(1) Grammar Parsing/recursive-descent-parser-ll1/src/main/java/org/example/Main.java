package org.example;

import org.example.LL1ParserPredictSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        List<String> testStrings = loadTestStringsFromFile("input.txt");

        if (testStrings == null) {
            System.out.println("Error loading test strings.");
            return;
        }

//        List<String> testStrings = Arrays.asList(
//                "aaaeeeddcb$", "aaaeeeddccb$", "aaaeeeddcccb$", "adbdd$", "adbddbc$",
//                "adbddbd$", "adcdcdcaccdc$", "adcdcdcadccc$", "adcdcdcadcdc$", "adddcbaddc$",
//                "adddcdddca$", "bbbbaaaecd$", "bbbbaaebcd$", "bbbbaebbcd$", "bdcacdc$",
//                "bdcadcc$", "bdcadcdc$", "bdccdccacdcdcc$", "bdccdccadcccdc$", "bdccdccadccdcc$",
//                "bedcbdcaadccddc$", "bedcbdcaadcdccd$", "bedcbdcaadcdcdc$", "dabdebcebd$",
//                "dabdebdebc$", "dabdebdebd$", "eaaaabbdace$", "eaaaabbdaec$", "eaaaabbdaee$",
//                "eebaddcddddc$"
//        );

        LL1ParserPredictSet ll1ParserPredictSet = new LL1ParserPredictSet();

        for (String test : testStrings) {
            boolean result = ll1ParserPredictSet.parseString(test);
            System.out.println(test + " -> " + (result ? "Accepted" : "Rejected"));
        }
    }

    private List<String> loadTestStringsFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Failed to read " + fileName + ": " + e.getMessage());
            return null;
        }
    }
}
