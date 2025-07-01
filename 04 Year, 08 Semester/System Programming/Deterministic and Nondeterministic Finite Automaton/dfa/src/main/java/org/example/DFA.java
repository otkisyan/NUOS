package org.example;

import java.util.*;

/**
 * Deterministic Finite Automaton
 * Детерминированным конечным автоматом называется такой автомат, в котором
 * для каждой последовательности входных символов существует лишь одно
 * состояние,
 * в которое автомат может перейти из текущего.
 * <br>
 * <br>
 * Это конечный автомат, принимающий или отклоняющий заданную строку символов
 * путём
 * прохождения через последовательность состояний, определённых строкой.
 * Имеет единственную последовательность состояний во время работы.
 * <p>
 * Детерминированный конечный автомат M является 5-кортежем (Q, Σ, δ, q0, F)
 * состоящим из
 * <br>
 * - конечное множество состояний Q
 * <br>
 * - конечное множество входных символов, называемое алфавитом Σ
 * <br>
 * - функция переходов δ: Q × Σ → Q
 * <br>
 * - начальное состояние q0 ∈ Q
 * <br>
 * - множество конечных состояний F ⊆ Q
 */
public class DFA {
    private final Set<Set<String>> states;
    private final Set<String> startState;
    private final Set<Set<String>> finalStates;
    // Map <Исходное состояние, Map<Входной символ, Выходное состояние>>
    private final Map<Set<String>, Map<String, Set<String>>> transitionTable;

    /**
     * Конструктор, для инициализации детерминированного конечного автомата.
     * <p>
     * Конструктор заполняет таблицу переходов для каждого состояния и
     * соответствующих символов.
     *
     * @param states      множество состояний автомата
     * @param transitions список переходов автомата
     * @param startState  начальное состояние
     * @param finalStates множество конечных состояний
     */
    public DFA(Set<Set<String>> states, List<String[]> transitions, Set<String> startState,
            Set<Set<String>> finalStates) {
        this.states = states;
        this.startState = startState;
        this.finalStates = finalStates;
        this.transitionTable = new HashMap<>();

        for (Set<String> state : states) {
            transitionTable.put(state, new HashMap<>());
        }

        for (String[] transition : transitions) {
            Set<String> from = parseState(transition[0]);
            String input = transition[1];
            Set<String> to = parseState(transition[2]);

            if (transitionTable.get(from) != null) {
                transitionTable.get(from).put(input, to);
            }
        }
    }

    /**
     * Проверяет, принимает ли детерминированный конечный автомат заданную строку.
     * <p>
     * Метод поочередно обрабатывает символы входной строки, начиная с начального
     * состояния.
     * Для каждого символа строки выполняется переход в новое состояние согласно
     * таблице переходов.
     * Если на каком-то шаге не удается найти подходящий переход для текущего
     * символа, автомат сразу возвращает <code>false</code>,
     * что означает, что строка не принимается автоматом.
     * <p>
     * После обработки всех символов строки проверяется, попал ли автомат в одно из
     * конечных состояний.
     * Если да, то метод возвращает <code>true</code>, иначе — <code>false</code>.
     *
     * @param inputString строка, которую необходимо проверить
     * @return <code>true</code>, если строка принимается автоматом, иначе
     *         <code>false</code>
     */
    public boolean accept(String inputString) {
        Set<String> currentState = startState;
        for (char symbol : inputString.toCharArray()) {
            String symbolStr = String.valueOf(symbol);
            if (!transitionTable.get(currentState).containsKey(symbolStr)) {
                return false;
            }
            currentState = transitionTable.get(currentState).get(symbolStr);
        }
        return finalStates.contains(currentState);
    }

    /**
     * Выводит таблицу переходов детерминированного конечного автомата в консоль.
     * <p>
     * Метод перебирает таблицу переходов состояний конечного автомата и для каждого
     * состояния выводит
     * информацию о переходах на другие состояния для каждого входного символа
     * Пример вывода:
     * 
     * <pre>
     * state1 -> a state2
     * state1 -> b state3
     * </pre>
     */
    public void printTransitionTable() {
        for (Map.Entry<Set<String>, Map<String, Set<String>>> entry : transitionTable.entrySet()) {
            Set<String> state = entry.getKey();
            Map<String, Set<String>> transitions = entry.getValue();

            for (Map.Entry<String, Set<String>> transition : transitions.entrySet()) {
                String symbol = transition.getKey();
                Set<String> nextState = transition.getValue();

                System.out.println(state + " -> " + symbol + " " + nextState);
            }
        }
    }

    /**
     * Преобразует строковое представление состояния в множество строк.
     * <p>
     * Метод удаляет фигурные скобки и пробелы из входной строки, затем разбивает её
     * по запятым
     * и формирует множество состояний.
     * <p>
     * Например, строка "{q1,q2}" будет преобразована в множество {"q1", "q2"}.
     *
     * @param stateStr строковое представление состояния в формате "{q1,q2}"
     * @return множество строк, представляющих состояние автомата
     */
    private Set<String> parseState(String stateStr) {
        stateStr = stateStr.replaceAll("[{} ]", "");
        String[] elements = stateStr.split(",");
        return new HashSet<>(Arrays.asList(elements));
    }
}
