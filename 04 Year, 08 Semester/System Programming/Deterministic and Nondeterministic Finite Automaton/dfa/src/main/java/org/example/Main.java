package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run2();
    }

    private static List<String> loadTestStrings(String filename) {
        List<String> testStrings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                testStrings.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return testStrings;
    }

    private static ArrayList<String[]> loadTransitions(String filename) {
        ArrayList<String[]> transitions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 3) {
                    transitions.add(parts);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return transitions;
    }

    private static Set<Set<String>> loadDFAStates(String filename) {
        Set<String> states = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 3) {
                    states.add(parts[0]); // Добавляем начальное состояние
                    states.add(parts[2]); // Добавляем конечное состояние
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        // Преобразуем Set<String> в Set<Set<String>>
        Set<Set<String>> dfaStates = new HashSet<>();
        for (String state : states) {
            dfaStates.add(new HashSet<>(Collections.singleton(state)));
        }

        return dfaStates;
    }

    public void run() {

        Set<Set<String>> dfaStates = loadDFAStates("dfa_transitions.txt");

        ArrayList<String[]> transitions = loadTransitions("dfa_transitions.txt");

        Set<String> startState = new HashSet<>(Collections.singleton("S"));
        Set<Set<String>> finalStates = new HashSet<>(Collections.singleton(new HashSet<>(Collections.singleton("C"))));

        DFA dfa = new DFA(dfaStates, transitions, startState, finalStates);

        System.out.println("Таблица переходов: ");
        dfa.printTransitionTable();

        List<String> testStrings = loadTestStrings("test_strings.txt");
        for (String test : testStrings) {
            System.out.println("String: " + test + " -> " + (dfa.accept(test) ? "Accepted" : "Rejected"));
        }
    }

    public void run2(){
        List<String[]> nfaTransitions = loadTransitions("nfa_transitions.txt");

        // Set<String> nfaStates = new HashSet<>(Arrays.asList("S", "A", "B", "C", "D"));
        Set<String> finalStates = new HashSet<>(Collections.singleton("C"));
        String startState = "S";

        NFAtoDFA nfAtoDFA = new NFAtoDFA();
        DFA dfa = nfAtoDFA.convertNFAtoDFA(nfaTransitions, startState, finalStates);

        System.out.println("Таблица переходов ДКА: ");
        dfa.printTransitionTable();

        List<String> testStrings = loadTestStrings("test_strings.txt");
        for (String test : testStrings) {
            System.out.println("String: " + test + " -> " + (dfa.accept(test) ? "Accepted" : "Rejected"));
        }
    }
}