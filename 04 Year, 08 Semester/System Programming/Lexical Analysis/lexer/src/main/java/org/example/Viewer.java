package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Viewer {

    public Viewer() {

    }

    public void scan() {
        String filePath = "/Users/otkisyan/IdeaProjects/lexer/output.txt";

        try {
            String content = readFile(filePath);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        reader.close();
        return content.toString();
    }
}