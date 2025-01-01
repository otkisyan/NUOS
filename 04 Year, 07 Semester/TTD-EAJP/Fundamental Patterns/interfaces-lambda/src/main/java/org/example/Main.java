package org.example;

import org.example.color.Color;
import org.example.pear.*;
import org.example.predicate.Predicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        List<Pear> pears = new ArrayList<>();

        pears.add(new Pear(200.0, Color.GREEN));
        pears.add(new Pear(100.0, Color.GREEN));
        pears.add(new Pear(160.0, Color.YELLOW));
        pears.add(new Pear(120.0, Color.YELLOW));
        pears.add(new Pear(180.0, Color.GREEN));
        // main.run2();

        List<Pear> filtered = filterPears(pears, new PearYellowColorAndHeavyPredicate());
        printPears(pears, new PearFancyFormatter());
        printPears(pears, new PearSimpleFormatter());

       /* filterPears(pears, new PearPredicate() {
            @Override
            public boolean predicate(Pear pear) {
                return Color.YELLOW.equals(pear.getColor()) && pear.getWeight() > 150;
            }
        });*/

        filterPears(pears, (Pear pear) -> Color.YELLOW.equals(pear.getColor()) && pear.getWeight() > 150);

        /*pears.sort(new Comparator<Pear>() {
            @Override
            public int compare(Pear o1, Pear o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            };
        });*/

        pears.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

        System.out.println(pears);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(8);

        // List<Integer> result = filter(numbers, (Integer i) -> i % 2 == 0);
    }

    public static void printPears(List<Pear> pears, PearFormatter f) {
        for (Pear pear : pears) {
            String output = f.accept(pear);
            System.out.println(output);
        }
    }

    public static List<Pear> filterPears(List<Pear> pears, PearPredicate p) {
        List<Pear> filteredPears = new ArrayList<>();
        for (Pear pear : pears) {
            if (p.predicate(pear)) {
                filteredPears.add(pear);
            }
        }
        return filteredPears;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> filteredPears = new ArrayList<>();
        for (T t : list) {
            if (p.predicate(t)) {
                filteredPears.add(t);
            }
        }
        return filteredPears;
    }

    public void run2() {

        System.out.println(execute((a, b) -> a + b));
        System.out.println(execute((a, b) -> a * b));

        ITest iTest = (a, b) -> a / b;
        iTest.sum(2, 5);
    }

    public int execute(ITest test) {

        return test.sum(42, 11);
    }

    @FunctionalInterface
    public interface ITest {
        int sum(int a, int b);
    }
}