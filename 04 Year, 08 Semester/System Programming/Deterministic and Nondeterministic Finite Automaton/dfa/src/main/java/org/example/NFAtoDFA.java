package org.example;

import java.util.*;

public class NFAtoDFA {
    /**
     * Преобразует недетерминированный конечный автомат (НКА) в детерминированный конечный автомат (ДКА)
     * с использованием алгоритма подмножеств (Powerset Construction).
     * <br><br>
     * Алгоритм подмножеств используется для построения ДКА на основе НКА. Основная идея заключается в том,
     * что каждое состояние ДКА представляет собой множество состояний НКА, которые могут быть достигнуты
     * из одного состояния при обработке входного символа.
     *
     * <p><b>Основные этапы алгоритма:</b></p>
     * 1. Начальное состояние ДКА – это множество, содержащее начальное состояние НКА.<br>
     * 2. Для каждого состояния ДКА вычисляются возможные переходы по каждому входному символу,
     *    объединяя множество состояний НКА, в которые можно попасть.<br>
     * 3. Если полученное множество состояний не встречалось ранее, оно добавляется в множество состояний ДКА.<br>
     * 4. Процесс повторяется для всех новых состояний, пока не будут обработаны все возможные переходы.<br>
     * 5. Состояние ДКА считается конечным, если хотя бы одно из входящих в него состояний принадлежит множеству
     *    конечных состояний НКА.
     *
     * @param nfaTransitions Список переходов НКА в формате {from, input, to}, где:
     *                       - from  — исходное состояние,
     *                       - input — входной символ,
     *                       - to    — целевое состояние.
     * @param startState  Начальное состояние НКА.
     * @param finalStates Множество конечных состояний НКА.
     * @return Объект DFA, представляющий детерминированный конечный автомат.
     */
    public DFA convertNFAtoDFA(
            List<String[]> nfaTransitions,
            String startState,
            Set<String> finalStates) {

        Map<String, Map<String, Set<String>>> nfaTransitionTable = buildNfaTransitionTable(nfaTransitions);
        Set<Set<String>> dfaStates = new HashSet<>();
        Set<Set<String>> dfaFinalStates = new HashSet<>();
        Map<Set<String>, Map<String, Set<String>>> dfaTransitions = new HashMap<>();
        // Начальное состояние ДКА — это множество, содержащее начальное состояние НКА
        Set<String> dfaStartState = new HashSet<>(Collections.singleton(startState));

        Queue<Set<String>> queue = new LinkedList<>();
        queue.add(dfaStartState);
        dfaStates.add(dfaStartState);

        // Обрабатываем состояния ДКА
        // Начинаем с начального состояния и постепенно строим новые состояния.
        // Каждое новое состояние добавляется в очередь и обрабатывается по очереди, прежде чем переходить дальше.
        // Это типичный признак обхода в ширину (BFS).
        while (!queue.isEmpty()) {
            Set<String> currentState = queue.poll();
            dfaTransitions.putIfAbsent(currentState, new HashMap<>());

            // Собираем все возможные переходы для текущего состояния по каждому символу,
            // то-есть вычисляем множество состояний, куда можно попасть из currentState по символу input
            // Map<Вход, Set<Выходные состояния>>
            Map<String, Set<String>> newTransitions = new HashMap<>();

            for (String nfaState : currentState) {
                if (nfaTransitionTable.containsKey(nfaState)) {
                    for (Map.Entry<String, Set<String>> entry : nfaTransitionTable.get(nfaState).entrySet()) {
                        String input = entry.getKey();
                        Set<String> nextStates = entry.getValue();
                        newTransitions.putIfAbsent(input, new HashSet<>());
                        newTransitions.get(input).addAll(nextStates);
                    }
                }
            }

            // Добавляем переходы в таблицу переходов ДКА
            for (Map.Entry<String, Set<String>> entry : newTransitions.entrySet()) {
                String input = entry.getKey();
                Set<String> nextState = entry.getValue();
                dfaTransitions.get(currentState).put(input, nextState);

                // Если новое состояние ещё не было обработано, добавляем его в очередь
                if (!dfaStates.contains(nextState)) {
                    dfaStates.add(nextState);
                    queue.add(nextState);
                }
            }

            // Проверяем, является ли текущее состояние ДКА конечным
            // В ДКА состояние считается конечным, если хотя бы одно из состояний,
            // составляющих его, является конечным в исходном НКА.
            for (String finalState : finalStates) {
                if (currentState.contains(finalState)) {
                    dfaFinalStates.add(currentState);
                    // Как только найдено хотя бы одно состояние из finalStates
                    // внутри currentState, значит этот currentState точно является конечным.
                    // Нет необходимости продолжать проверку оставшихся состояний, поэтому завершаем цикл досрочно.
                    break;
                }
            }
        }

        // Создаю и возвращаю ДКА
        return new DFA(dfaStates, convertTransitionsToList(dfaTransitions), dfaStartState, dfaFinalStates);
    }

    /**
     * Строит таблицу переходов для недетерминированного конечного автомата (НКА).
     *
     * <p>Таблица переходов представляется в виде структуры данных:
     * <pre>
     * {
     *   "state1": { "input1": {"state2", "state3"}, "input2": {"state4"} },
     *   "state2": { "input1": {"state3"} }
     * }
     * </pre>
     * где:
     * - Ключ первого уровня — это исходное состояние НКА.
     * - Ключ второго уровня — входной символ.
     * - Значение — множество состояний, в которые можно перейти по данному символу.
     *
     * @param nfaTransitions Список переходов НКА в формате массива {from, input, to}, где:
     *                       - from  — исходное состояние,
     *                       - input — входной символ,
     *                       - to    — целевое состояние.
     * @return Таблица переходов НКА в виде {@code Map<String, Map<String, Set<String>>>}.
     */
    private Map<String, Map<String, Set<String>>> buildNfaTransitionTable (List<String[]> nfaTransitions) {
        Map<String, Map<String, Set<String>>> nfaTransitionTable = new HashMap<>();

        // Формирую таблицу переходов вида {From = {symbol = [To,...], symbol = ...}
        // {A={я=[B, C]}, B={ч=[C], о=[D]}, S={б=[B], і=[B]}, C={<=[C], о=[A]}, D={р=[A, C]}}
        for (String[] transition : nfaTransitions) {
            String from = transition[0];
            String input = transition[1];
            String to = transition[2];

            nfaTransitionTable.putIfAbsent(from, new HashMap<>());
            nfaTransitionTable.get(from).putIfAbsent(input, new HashSet<>());
            nfaTransitionTable.get(from).get(input).add(to);
        }
        return nfaTransitionTable;
    }

    /**
     * Преобразует таблицу переходов детерминированного конечного автомата (ДКА) в список строковых массивов.
     *
     * <p>Каждый переход ДКА представлен в виде массива из трех элементов:
     * <pre>
     * ["fromState", "input", "toState"]
     * </pre>
     * где:
     * - fromState — множество состояний НКА, объединённое в строку через запятую.
     * - input     — входной символ.
     * - toState   — множество состояний НКА, в которые осуществляется переход, объединённое в строку через запятую.
     *
     * <p>Пример:
     * <pre>
     * Если dfaTransitions содержит:
     * { {A, B} -> {"0": {C}, "1": {D, E}} }
     *
     * Метод преобразует это в список:
     * [ ["A,B", "0", "C"], ["A,B", "1", "D,E"] ]
     * </pre>
     *
     * @param dfaTransitions Таблица переходов ДКА в виде {@code Map<Set<String>, Map<String, Set<String>>>}.
     * @return Список переходов ДКА в формате {@code List<String[]>}.
     */
    private List<String[]> convertTransitionsToList(Map<Set<String>, Map<String, Set<String>>> dfaTransitions) {
        List<String[]> dfaTransitionList = new ArrayList<>();
        for (Map.Entry<Set<String>, Map<String, Set<String>>> entry : dfaTransitions.entrySet()) {
            Set<String> fromState = entry.getKey();
            Map<String, Set<String>> transitions = entry.getValue();
            for (Map.Entry<String, Set<String>> transition : transitions.entrySet()) {
                String input = transition.getKey();
                Set<String> toState = transition.getValue();
                // Сериализация состояний в строки
                String fromStr = String.join(",", fromState);
                String toStr = String.join(",", toState);
                dfaTransitionList.add(new String[]{fromStr, input, toStr});
            }
        }
        return dfaTransitionList;
    }
}