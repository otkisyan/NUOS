package org.example;

import org.example.dto.LexerResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private final List<Token> tokens = new ArrayList<>();
    private final Pattern tokenPattern;

    public LexicalAnalyzer() {
        tokens.add(new Token("if", TokenType.KEYWORD, (byte) 1, "\\bif\\b"));
        tokens.add(new Token("else", TokenType.KEYWORD, (byte) 2, "\\belse\\b"));
        tokens.add(new Token("while", TokenType.KEYWORD, (byte) 3, "\\bwhile\\b"));
        tokens.add(new Token("for", TokenType.KEYWORD, (byte) 4, "\\bfor\\b"));
        tokens.add(new Token("switch", TokenType.KEYWORD, (byte) 5, "\\bswitch\\b"));
        tokens.add(new Token("case", TokenType.KEYWORD, (byte) 6, "\\bcase\\b"));
        tokens.add(new Token("break", TokenType.KEYWORD, (byte) 7, "\\bbreak\\b"));
        tokens.add(new Token("default", TokenType.KEYWORD, (byte) 8, "\\bdefault\\b"));
        tokens.add(new Token("whitespace", TokenType.SEPARATOR, (byte) 9, "[\\s]+"));

        // Создаем общий regex из всех паттернов токенов
        StringBuilder regexBuilder = new StringBuilder();
        for (Token token : tokens) {
            if (!regexBuilder.isEmpty()) {
                regexBuilder.append("|"); // Добавляем | между токенами
            }
            regexBuilder.append("(").append(token.getRegex()).append(")");
        }
        this.tokenPattern = Pattern.compile(regexBuilder.toString());
    }

    public LexerResult analyzeCode(String code) {
        code = code.replaceAll("\n+", " ");
        StringBuilder result = new StringBuilder();
        List<Token> matchedTokens = new ArrayList<>();

        Matcher matcher = tokenPattern.matcher(code);
        StringBuffer modifiedLine = new StringBuffer();

        // find() используется для поиска следующего совпадения с регулярным выражением в строке.
        // Он проверяет, есть ли в строке часть, которая соответствует регулярному выражению.
        while (matcher.find()) {
            // group() используется для получения строки, которая соответствует текущему совпадению в регулярном выражении
            // find() - ищет токены, group() - возвращает их
            String tokenStr = matcher.group();
            Token matchedToken = null;

            // Ищем совпадающий токен
            for (Token token : tokens) {
                if (tokenStr.matches(token.getRegex())) {
                    matchedToken = token;
                    break;
                }
            }

            if (matchedToken != null) {
                matchedTokens.add(matchedToken);
                if (matchedToken.getCode() == 9) {
                    matcher.appendReplacement(modifiedLine, " "); // Заменяем на пробел
                } else {
                    // Сначала добавляет в modifiedLine все символы до найденного токена,
                    // затем заменяет найденный токен на соответствующий код.
                    matcher.appendReplacement(modifiedLine, String.format("%8s", Integer.toBinaryString(matchedToken.getCode()))
                            .replace(' ', '0')); // Заменяем на
                }
//                } else {
//                    matcher.appendReplacement(modifiedLine, tokenStr); // Оставляем как есть
            }
        }

        // Когда больше нет совпадений, matcher.appendTail(result)
        // добавляет оставшуюся часть строки в modifiedLine.
        matcher.appendTail(modifiedLine);
        result.append(modifiedLine); // Добавляем обработанную строку

        return new LexerResult(result.toString().trim(), matchedTokens);
    }

    public String readInputFile(String inputFile) throws IOException {
        StringBuilder code = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            code.append(line).append("\n");
        }
        reader.close();
        return code.toString();
    }

    public void writeTextFile(String outputFile, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(data);
        writer.close();
    }

    public void writeLexemesFile(String lexemesFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(lexemesFile));
        writer.write("Ключові слова та їх байтові коди:\n");
        for (Token token : tokens) {
            writer.write(token.getValue() + " -> " + token.getCode() + "\n");
        }
        writer.close();
    }

    public void writeMatchedTokensFile(List<Token> matchedTokens, String matchedTokensFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(matchedTokensFile));
        writer.write("Найденные токены:\n");
        for (Token token : matchedTokens) {
            writer.write(token.getValue() + " -> " + token.getCode() + "\n");
        }
        writer.close();
    }
}
