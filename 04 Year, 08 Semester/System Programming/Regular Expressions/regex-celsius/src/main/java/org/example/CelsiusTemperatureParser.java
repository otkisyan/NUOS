package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelsiusTemperatureParser {

    // (?<=^|\\s) это позитивный просмотр-перед (positive lookbehind).
    // Он означает, что перед числом должно быть либо начало строки, либо пробельный символ.

    // -? - необязательный минус (для отрицательных температур).
    // \\d+ - одна или более цифр.
    // (?:\\.\\d+)? - необязательная десятичная часть (если есть).
    // C - обязательная буква C.
    // \\b - граница слова, чтобы гарантировать, что после C не будет других символов (например, Ca или любых других букв).

    // (?:\.\d+) - это незахватывающая группа, которая позволяет объединить несколько символов, но не сохраняет их отдельно при поиске.
    // \. - экранированная точка (. в регулярных выражениях означает "любой символ", поэтому нам нужно \. для обозначения именно точки).

    private final Pattern TEMP_PATTERN = Pattern.compile("(?<=^|\\s)-?\\d+(?:\\.\\d+)?C\\b");

    public List<String> extractTemperatures(String filePath) {
        List<String> temperatures = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = TEMP_PATTERN.matcher(line);
                while (matcher.find()) {
                    temperatures.add(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temperatures;
    }

}
