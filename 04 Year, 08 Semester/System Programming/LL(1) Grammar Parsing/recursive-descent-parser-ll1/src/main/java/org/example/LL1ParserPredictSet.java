package org.example;

/**
 * Реализует нисходящий синтаксический анализатор LL(1) с рекурсивным спуском.
 * В основе работы лежит выбор продукции на основе множества предсказаний (Predict Set).
 * <br><br>
 * Для LL(1) парсера (который использует один символ для принятия решения)
 * Predict Set для каждого нетерминала состоит из символов, которые могут быть первым символом продукции этого нетерминала.
 * Это множество символов, которые указывают, какое правило продукции нужно выбрать на основе текущего символа в строке.
 */

// S’ → S$
// S → CABB
// S → BCA
// A →
// A → aBA
// B → eB
// B → bC
// C → c
// C → d
public class LL1ParserPredictSet {
    private final char INPUT_END = '$';
    private String input;
    private int index;

    /**
     * Стартовый метод для разбора строки.
     * Инициализирует строку для разбора и вызывает основную функцию синтаксического анализа.
     *
     * @param test Строка, которую необходимо разобрать.
     * @return {@code true}, если строка успешно разобрана согласно грамматике, {@code false} в противном случае.
     */
    public boolean parseString(String test) {
        input = test;
        index = 0;
        return parse();
    }

    /**
     * Основной метод синтаксического анализа, начиная с нетерминала S.
     * Вызывает анализатор для нетерминала S и проверяет, что строка заканчивается символом конца ($).
     *
     * @return {@code true}, если строка успешно разобрана, {@code false} в противном случае.
     */
    private boolean parse() {
        return S() && match(INPUT_END);
    }

    /**
     * Метод для разбора нетерминала S.
     *
     * @return {@code true}, если строка соответствует одной из продукций для S, {@code false} в противном случае.
     */
    private boolean S() {
        if (lookahead('c') || lookahead('d')) { // First(C) = {c, d}
            return C() && A() && B() && B(); // CABB
        } else if (lookahead('e') || lookahead('b')) { // First(B) = {e, b}
            return B() && C() && A(); // BCA
        }
        return false;
    }

    /**
     * Метод для разбора нетерминала A.
     * A может быть пустым (ε), либо начинаться с 'a' и продолжаться по правилу aBA.
     *
     * @return {@code true}, если строка соответствует одной из продукций для A, {@code false} в противном случае.
     */
    private boolean A() {
        if (lookahead('a')) { // First(a) = {a}
            return match('a') && B() && A(); //aBA
        }
        return true; // A → ε
    }

    /**
     * Метод для разбора нетерминала B.
     * B может быть либо eB, либо bC.
     *
     * @return {@code true}, если строка соответствует одной из продукций для B, {@code false} в противном случае.
     */
    private boolean B() {
        if (lookahead('e')) { // First(e) = {e}
            return match('e') && B(); //eB
        } else if (lookahead('b')) { // First(b) = {b}
            return match('b') && C(); //bC
        }
        return false;
    }

    /**
     * Метод для разбора нетерминала C.
     * C может быть либо символом 'c', либо символом 'd'.
     *
     * @return {@code true}, если строка соответствует одной из продукций для C, {@code false} в противном случае.
     */
    private boolean C() {
        return match('c') || match('d'); // First(C) = {c, d}
    }

    /**
     * Проверяет, соответствует ли текущий символ в строке ожидаемому символу.
     * Если текущий символ совпадает с ожидаемым, индекс строки увеличивается на 1,
     * что позволяет продолжить анализ следующего символа. Если текущий символ не соответствует
     * ожидаемому, метод возвращает {@code false}, не изменяя индекс.
     *
     * @param expected Символ, который ожидается на текущей позиции в строке.
     * @return {@code true}, если текущий символ соответствует ожидаемому символу и индекс был
     *         увеличен на 1, {@code false} в случае несоответствия.
     */
    private boolean match(char expected) {
        if (index < input.length() && input.charAt(index) == expected) {
            index++;
            return true;
        }
        return false;
    }

    /**
     * Проверяет, соответствует ли следующий символ в строке одному из ожидаемых.
     * Используется для определения, какой продукцией следует продолжить разбор.
     *
     * @param expected Символ, который ожидается на следующей позиции в строке.
     * @return {@code true}, если текущий символ соответствует ожидаемому, {@code false} в противном случае.
     */
    private boolean lookahead(char expected) {
        return index < input.length() && input.charAt(index) == expected;
    }
}