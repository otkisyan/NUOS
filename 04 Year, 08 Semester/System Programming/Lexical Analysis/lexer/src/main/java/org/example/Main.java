package org.example;

import org.example.dto.LexerResult;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        Viewer viewer = new Viewer();
        String inputFile = "/Users/otkisyan/IdeaProjects/lexer/input.txt";
        String outputFile = "/Users/otkisyan/IdeaProjects/lexer/output.txt";
        String lexemesFile = "/Users/otkisyan/IdeaProjects/lexer/lexemes.txt";
        String matchedTokensFile = "/Users/otkisyan/IdeaProjects/lexer/matchedTokens.txt";

        try {
            String code = lexicalAnalyzer.readInputFile(inputFile);
            LexerResult lexerResult = lexicalAnalyzer.analyzeCode(code);
            lexicalAnalyzer.writeTextFile(outputFile, lexerResult.output());
            lexicalAnalyzer.writeLexemesFile(lexemesFile);
            lexicalAnalyzer.writeMatchedTokensFile(lexerResult.matchedTokens(), matchedTokensFile);
            System.out.println("Лексичний аналіз завершено.");
            viewer.scan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}